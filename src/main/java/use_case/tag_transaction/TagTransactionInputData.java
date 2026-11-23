
package use_case.tag_transaction;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * Input data for the Tag and Describe Transaction use case.
 */
public final class TagTransactionInputData {

    private final String transactionId;
    private final List<String> tagNames;
    private final String description;

    /**
     * Creates input data for updating a transaction.
     *
     * @param transactionId the transaction to update
     * @param tagNames the desired tags after saving
     * @param description the new description
     */
    public TagTransactionInputData(String transactionId,
                                   List<String> tagNames,
                                   String description) {

        this.transactionId = transactionId;

        Objects.requireNonNull(tagNames, "Tags cannot be null");
        this.tagNames = Collections.unmodifiableList(
                new ArrayList<>(tagNames));

        this.description = description;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public List<String> getTagNames() {
        return tagNames;
    }

    public String getDescription() {
        return description;
    }
}

