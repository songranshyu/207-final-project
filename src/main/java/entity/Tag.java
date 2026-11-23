package entity;

import java.util.Locale;
import java.util.Objects;

/**
 * Represents a tag that can be attached to a transaction.
 * Tag names are compared without case or surrounding spaces.
 */
public final class Tag {

    private final String name;
    private final String normalizedName;

    /**
     * Creates a new tag.
     *
     * @param name the tag name
     * @throws IllegalArgumentException if the tag name is empty
     */
    public Tag(String name) {
        Objects.requireNonNull(name, "Tag name cannot be null");

        String cleanedName = name.trim();

        if (cleanedName.isEmpty()) {
            throw new IllegalArgumentException(
                    "Tag name cannot be empty");
        }

        this.name = cleanedName;
        this.normalizedName = cleanedName.toLowerCase(Locale.ROOT);
    }

    /**
     * Returns the display name of the tag.
     *
     * @return the tag name
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the normalized name of the tag.
     *
     * @return the normalized name
     */
    public String getNormalizedName() {
        return normalizedName;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }

        if (!(object instanceof Tag)) {
            return false;
        }

        Tag other = (Tag) object;
        return normalizedName.equals(other.normalizedName);
    }

    @Override
    public int hashCode() {
        return normalizedName.hashCode();
    }

    @Override
    public String toString() {
        return name;
    }
}

