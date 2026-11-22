package use_case.itemize_transaction;

/**
 * The Itemize transaction Use Case
 */
public interface ItemizeInputBoundary {

    /**
     * Execute the Itemize Transaction Use Case
     * @param itemizeInputData the input data for this use case
     */
    void execute(ItemizeInputData itemizeInputData);
}

