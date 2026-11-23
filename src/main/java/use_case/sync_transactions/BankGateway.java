package use_case.sync_transactions;

/**
 * What the sync use case needs from a bank: connect once, then ask for changes since the last sync.
 */
public interface BankGateway {

    /**
     * Connects a bank account.
     *
     * @return the access token for this bank connection; save it and treat it like a password
     * @throws BankGatewayException if the bank service can't be reached or refuses the connection
     */
    String connectToBank() throws BankGatewayException;

    /**
     * Gets every transaction added, changed or removed since {@code cursor}.
     *
     * @param accessToken the access token from {@link #connectToBank()}
     * @param cursor where the last complete sync ended, or null to get the whole history
     * @return the changes, and the cursor to save once they have been applied
     * @throws BankGatewayException if the bank service can't be reached, refuses the request, or isn't ready
     */
    BankSyncResult fetchChanges(String accessToken, String cursor) throws BankGatewayException;
}
