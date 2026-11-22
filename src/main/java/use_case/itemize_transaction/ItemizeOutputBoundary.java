package use_case.itemize_transaction;

/**
 * The output boundary for the itemize transaction Use Case
 */

public interface ItemizeOutputBoundary {
    /**
     * Prepares the success view for the Itemize transaction Use Case
     * @param outputData the output data
     */
    void prepareSuccessView(ItemizeOutputData outputData);

    /**
     * Prepares the failure view for the Itemize transaction Use Case
     */
    void prepareFailView(String errorMessage);
}