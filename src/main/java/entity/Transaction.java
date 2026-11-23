
package entity;

import java.time.LocalDate;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

/**
 * Represents a bank or manually recorded transaction.
 */
public final class Transaction {

    private static final int MAX_DESCRIPTION_LENGTH = 200;

    /**
     * Identifies the source of a transaction.
     */
    public enum Source {
        BANK,
        MANUAL
    }

    private final String id;
    private final Source source;
    private final String plaidTransactionId;

    private LocalDate date;
    private String storeName;
    private Money total;
    private String description = "";

    private final Set<Tag> tags = new LinkedHashSet<>();

    /**
     * Creates a transaction.
     *
     * @param id unique transaction ID
     * @param source BANK or MANUAL
     * @param plaidTransactionId Plaid ID, null for manual entries
     * @param date transaction date
     * @param storeName store name
     * @param total transaction amount
     */
    public Transaction(String id, Source source,
                       String plaidTransactionId, LocalDate date,
                       String storeName, Money total) {

        this.id = requireText(id, "Transaction ID");
        this.source = Objects.requireNonNull(source, "Source cannot be null");

        if (source == Source.BANK) {
            this.plaidTransactionId = requireText(
                    plaidTransactionId, "Plaid transaction ID");
        }
        else {
            this.plaidTransactionId = null;
        }

        validateDate(date);
        this.date = date;
        this.storeName = requireText(storeName, "Store name");
        this.total = Objects.requireNonNull(total, "Total cannot be null");

        if (source == Source.MANUAL && total.getCents() <= 0) {
            throw new IllegalArgumentException(
                    "Manual transaction total must be positive");
        }
    }

    /**
     * Convenience constructor for a manual transaction.
     */
    public Transaction(String id, LocalDate date,
                       String storeName, Money total) {
        this(id, Source.MANUAL, null, date, storeName, total);
    }

    public String getId() {
        return id;
    }

    public Source getSource() {
        return source;
    }

    public String getPlaidTransactionId() {
        return plaidTransactionId;
    }

    public LocalDate getDate() {
        return date;
    }

    public String getStoreName() {
        return storeName;
    }

    public Money getTotal() {
        return total;
    }

    public String getDescription() {
        return description;
    }

    /**
     * Returns the transaction's tags without allowing
     * external code to modify the set directly.
     */
    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(new LinkedHashSet<>(tags));
    }

    /**
     * Updates the description, up to 200 characters.
     */
    public void setDescription(String newDescription) {
        String value = newDescription == null ? "" : newDescription;

        if (value.length() > MAX_DESCRIPTION_LENGTH) {
            throw new IllegalArgumentException(
                    "Description cannot exceed 200 characters");
        }

        this.description = value;
    }

    /**
     * Adds a tag. Duplicate tags are ignored.
     *
     * @return true if a new tag was added
     */
    public boolean addTag(Tag tag) {
        return tags.add(Objects.requireNonNull(tag, "Tag cannot be null"));
    }

    /**
     * Removes a tag.
     *
     * @return true if the tag was removed
     */
    public boolean removeTag(Tag tag) {
        return tags.remove(Objects.requireNonNull(tag, "Tag cannot be null"));
    }

    /**
     * Updates bank-provided information without changing
     * the user's description or tags.
     */
    public void updateBankDetails(LocalDate newDate,
                                  String newStoreName, Money newTotal) {
        if (source != Source.BANK) {
            throw new IllegalStateException(
                    "Only bank transactions can be synced");
        }

        validateDate(newDate);
        String validatedStore = requireText(newStoreName, "Store name");
        Money validatedTotal = Objects.requireNonNull(
                newTotal, "Total cannot be null");

        this.date = newDate;
        this.storeName = validatedStore;
        this.total = validatedTotal;
    }

    private static void validateDate(LocalDate date) {
        Objects.requireNonNull(date, "Date cannot be null");

        if (date.isAfter(LocalDate.now())) {
            throw new IllegalArgumentException(
                    "Transaction date cannot be in the future");
        }
    }

    private static String requireText(String value, String fieldName) {
        if (value == null || value.trim().isEmpty()) {
            throw new IllegalArgumentException(
                    fieldName + " cannot be empty");
        }
        return value.trim();
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (!(object instanceof Transaction)) {
            return false;
        }
        Transaction other = (Transaction) object;
        return id.equals(other.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
