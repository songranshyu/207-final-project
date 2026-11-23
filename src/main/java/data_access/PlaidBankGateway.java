package data_access;

import use_case.sync_transactions.BankGateway;
import use_case.sync_transactions.BankGatewayException;
import use_case.sync_transactions.BankSyncResult;

public class PlaidBankGateway implements BankGateway {
    @Override
    public String connectToBank() throws BankGatewayException {
        return "";
    }

    @Override
    public BankSyncResult fetchChanges(String accessToken, String cursor) throws BankGatewayException {
        return null;
    }
}
