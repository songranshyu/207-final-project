package data_access;

import com.plaid.client.ApiClient;
import com.plaid.client.model.ItemPublicTokenExchangeRequest;
import com.plaid.client.model.Products;
import com.plaid.client.model.SandboxPublicTokenCreateRequest;
import com.plaid.client.request.PlaidApi;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;
import use_case.sync_transactions.BankGateway;
import use_case.sync_transactions.BankGatewayException;
import use_case.sync_transactions.BankSyncResult;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class PlaidBankGateway implements BankGateway {
    private final PlaidApi plaidApi;
    private static final String SANDBOX_INSTITUTION_ID = "ins_109508";

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
     * @param <T> the type of Plaid's answer for this request
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
        return null;
    }
}
