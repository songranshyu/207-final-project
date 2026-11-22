package use_case.itemize_transaction;

import java.util.List;

public class ItemizeInputData {

    private final String transactionId;
    private final List<String> names;
    private final List<Long> prices;

    /**
     * ItemizeInputData instance
     * @param transactionId the ID of the transaction
     * @param names the list of line item names
     * @param prices this list of the line item price
     */
    public ItemizeInputData(String transactionId, List<String> names, List<Long> prices) {
        this.transactionId = transactionId;
        this.names = names;
        this.prices = prices;
    }

    String getTransactionId() { return transactionId; }

    List<String> getNames() { return names; }

    List<Long> getPrices() { return prices; }
}

