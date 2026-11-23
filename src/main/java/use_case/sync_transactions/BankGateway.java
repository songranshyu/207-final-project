package use_case.sync_transactions;

public interface BankGateway {
    String connectToBank() throws BankGatewayException;
    BankSyncResult fetchChanges(String accessToken, String cursor) throws BankGatewayException;
}
