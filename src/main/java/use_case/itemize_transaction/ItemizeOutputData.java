package use_case.itemize_transaction;
//need to change / come back when money class r made
import entity.Money;

/**
 * Output Data for the Itemize Transaction Use Case
 */
public class ItemizeOutputData {

    private final String transactionId;
    private final Money itemizedTotal;
    private final Money remaining;

    /***
     * ItemizeOutputData instance
     * @param transactionId the ID of the itemize transaction
     * @param itemizedTotal the total amount in line items
     * @param remaining     the remain amount that are not in line item
     */

    public ItemizeOutputData(String transactionId, Money itemizedTotal, Money remaining) {
        this.transactionId = transactionId;
        this.itemizedTotal = itemizedTotal;
        this.remaining = remaining;
    }

    public String getTransactionId() { return transactionId; }

    public Money getItemizedTotal() { return itemizedTotal; }

    public Money getRemaining() { return  remaining; }
}
