package data_access;

import com.plaid.client.ApiClient;
import com.plaid.client.model.ItemPublicTokenExchangeRequest;
import com.plaid.client.model.PersonalFinanceCategoryVersion;
import com.plaid.client.model.Products;
import com.plaid.client.model.RemovedTransaction;
import com.plaid.client.model.SandboxPublicTokenCreateRequest;
import com.plaid.client.model.Transaction;
import com.plaid.client.model.TransactionsSyncRequest;
import com.plaid.client.model.TransactionsSyncRequestOptions;
import com.plaid.client.model.TransactionsSyncResponse;
import com.plaid.client.model.TransactionsUpdateStatus;
import com.plaid.client.request.PlaidApi;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;
import use_case.sync_transactions.BankGateway;
import use_case.sync_transactions.BankGatewayException;
import use_case.sync_transactions.BankSyncResult;
import use_case.sync_transactions.BankTransaction;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class PlaidBankGateway implements BankGateway {
    private final PlaidApi plaidApi;
    private static final String SANDBOX_INSTITUTION_ID = "ins_109508";

    /**
     * How many times to ask again while Plaid is still preparing a newly connected bank's data.
     */
    private static final int MAX_READY_CHECKS = 15;

    /**
     * How long to wait between those checks, in milliseconds (15 x 2 s = at most 30 s).
     */
    private static final long READY_CHECK_DELAY_MILLIS = 2000;

    public PlaidBankGateway(String clientId, String secret) {
        Map<String, String> userCredentials = Map.of(
                "clientId", clientId,
                "secret", secret
        );

        ApiClient apiClient = new ApiClient(userCredentials);

        apiClient.setPlaidAdapter(ApiClient.Sandbox);

        this.plaidApi = apiClient.createService(PlaidApi.class);
    }

    /**
     * Sends one request to Plaid and returns Plaid's answer.
     *
     * @param request the unsent Plaid request
     * @param <T>     the type of Plaid's answer for this request
     * @return Plaid's answer
     * @throws BankGatewayException if Plaid can't be reached or rejects the request
     */
    private static <T> T sendPlaidRequest(Call<T> request) throws BankGatewayException {
        try {
            Response<T> response = request.execute();

            if (response.isSuccessful() && response.body() != null) {
                return response.body();
            }
            // The error body is an open network stream, so it must be closed when we're done with it.
            ResponseBody errorBody = response.errorBody();
            try {
                String plaidError = "no details";
                if (errorBody != null) {
                    plaidError = errorBody.string();
                }
                throw new BankGatewayException("Plaid error (HTTP " + response.code() + "): " + plaidError);
            } finally {
                if (errorBody != null) {
                    errorBody.close();
                }
            }
        } catch (IOException e) {
            throw new BankGatewayException("Couldn't reach Plaid", e);
        }
    }

    private static BankTransaction toBankTransaction(Transaction plaidTransaction) {

        String storeName = plaidTransaction.getMerchantName();
        if (storeName == null || storeName.isEmpty()) {
            storeName = plaidTransaction.getName();
        }

        String category = null;
        if (plaidTransaction.getPersonalFinanceCategory() != null) {
            category = plaidTransaction.getPersonalFinanceCategory().getPrimary();
        }


        return new BankTransaction(
                plaidTransaction.getTransactionId(),
                plaidTransaction.getDate(),
                storeName,
                toCents(plaidTransaction.getAmount()),
                plaidTransaction.getIsoCurrencyCode(),
                category,
                Boolean.TRUE.equals(plaidTransaction.getPending())
        );
    }

    /**
     * Converts Plaid's dollar amount to whole cents without double rounding errors.
     *
     * <p>{@code (long) (amount * 100)} would turn 19.99 into 1998, because a double can't store 19.99 exactly.
     * {@code BigDecimal.valueOf} reads the amount as the decimal "19.99", so the result is exactly 1999.
     *
     * @param amount the amount in dollars, as Plaid sends it (positive means money out)
     * @return the same amount in cents
     */
    private static long toCents(double amount) {
        return BigDecimal.valueOf(amount)
                .movePointRight(2)
                .setScale(0, RoundingMode.HALF_UP)
                .longValueExact();
    }


    @Override
    public String connectToBank() throws BankGatewayException {
        SandboxPublicTokenCreateRequest createRequest = new SandboxPublicTokenCreateRequest()
                .institutionId(SANDBOX_INSTITUTION_ID)
                .initialProducts(List.of(Products.TRANSACTIONS));

        String publicToken = sendPlaidRequest(plaidApi.sandboxPublicTokenCreate(createRequest)).getPublicToken();

        ItemPublicTokenExchangeRequest exchangeRequest = new ItemPublicTokenExchangeRequest()
                .publicToken(publicToken);
        return sendPlaidRequest(plaidApi.itemPublicTokenExchange(exchangeRequest)).getAccessToken();
    }

    @Override
    public BankSyncResult fetchChanges(String accessToken, String cursor) throws BankGatewayException {
        List<BankTransaction> added = new ArrayList<BankTransaction>();
        List<BankTransaction> modified = new ArrayList<BankTransaction>();
        List<String> removedIds = new ArrayList<String>();

        // Pin Plaid's category list to V2 so our category-to-tag map always gets the codes it expects.
        TransactionsSyncRequestOptions syncOptions = new TransactionsSyncRequestOptions()
                .personalFinanceCategoryVersion(PersonalFinanceCategoryVersion.V2);

        int notReadyChecks = 0;
        while (true) {
            TransactionsSyncRequest transactionsSyncRequest = new TransactionsSyncRequest()
                    .accessToken(accessToken)
                    .options(syncOptions);
            if (cursor != null) {
                transactionsSyncRequest.setCursor(cursor);
            }
            TransactionsSyncResponse latestFetch = sendPlaidRequest(plaidApi.transactionsSync(transactionsSyncRequest));

            // Right after a bank is connected, Plaid may still be loading its data. It says so either with the
            // status NOT_READY or with an empty next cursor (even when the status already looks complete).
            // Wait and ask again with the same cursor, but give up after MAX_READY_CHECKS tries.
            if (isNotReady(latestFetch)) {
                notReadyChecks++;
                if (notReadyChecks >= MAX_READY_CHECKS) {
                    throw new BankGatewayException("The bank is still preparing its data. Try again in a minute.");
                }
                pause();
                continue;
            }

            for (Transaction plaidTransaction : latestFetch.getAdded()) {
                added.add(toBankTransaction(plaidTransaction));
            }
            for (Transaction plaidTransaction : latestFetch.getModified()) {
                modified.add(toBankTransaction(plaidTransaction));
            }
            for (RemovedTransaction removedTransaction : latestFetch.getRemoved()) {
                removedIds.add(removedTransaction.getTransactionId());
            }

            cursor = latestFetch.getNextCursor();

            // Plaid sends at most about 100 transactions per page; keep asking until there are no more pages.
            if (!Boolean.TRUE.equals(latestFetch.getHasMore())) {
                return new BankSyncResult(added, modified, removedIds, cursor);
            }
        }
    }

    /**
     * Tells whether Plaid is still preparing the data, so this answer has nothing in it yet.
     *
     * @param syncResponse one answer from Plaid's sync call
     * @return true if we should wait and ask again
     */
    private static boolean isNotReady(TransactionsSyncResponse syncResponse) {
        final String nextCursor = syncResponse.getNextCursor();
        return syncResponse.getTransactionsUpdateStatus() == TransactionsUpdateStatus.NOT_READY
                || nextCursor == null || nextCursor.isEmpty();
    }

    /**
     * Waits a moment before asking Plaid again.
     *
     * @throws BankGatewayException if the wait is interrupted
     */
    private static void pause() throws BankGatewayException {
        try {
            Thread.sleep(READY_CHECK_DELAY_MILLIS);
        } catch (InterruptedException e) {
            // Keep the "interrupted" signal so whoever runs this thread can still see it.
            Thread.currentThread().interrupt();
            throw new BankGatewayException("Interrupted while waiting for the bank", e);
        }
    }
}
