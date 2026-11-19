package use_case.sync_transactions;

public class BankGatewayException extends Exception {
    public BankGatewayException(String message) {
        super(message);
    }
}
