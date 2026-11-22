package use_case.itemize_transaction;
//need to change / come back when Transaction class r made
import entity.Transaction;

/**
 * The Data Access Interface for the Itemize Transaction Use Case.
 */
public interface ItemizeDataAccessInterface {

    /**
     * Returns the user with the given username.
     * @param id the transaction ID
     * @return the transaction with the given ID
     **/
    Transaction get(String id);

    /**
     * Saves the transaction.
     * @param transaction the transaction to save
     */
    void save(Transaction transaction);
}