package entity;

//some line doesn't work yet cuz the other class wasn't create yet

import java.util.Objects;

public class LineItem {

    private final String name;
    private final int quantity;
    private final Money price;
    private Tag tag;

    /**
     * Line item is an individual item inside a purchase
     * Creates a new line item with the given non-empty name and non-empty password.
     * @param name the name of item
     * @param quantity the quantity of item
     * @param price the price in Money
     * @param tag the tag of the item (could be null)
     * @throws IllegalArgumentException if the name is empty or quantity is not positive or price are empty or negative
     */
    public LineItem(String name, int quantity, Money price, Tag tag) {
        if ("".equals(name)) {
            throw new IllegalArgumentException("Item name cannot be empty");
        }
        if (quantity < 1) {
            throw new IllegalArgumentException("Quantity must not be less than 1");
        }
        if (price == null) {
            throw new IllegalArgumentException("Price cannot be empty");
        }
        if (price.getAmount < 0) {
            //need to be change when money class is made
            throw new IllegalArgumentException("Price cannot be negative");
        }
        this.name = name;
        this.quantity = quantity;
        this.price = price;
        this.tag = tag;
    }

    public String getName() {
        return name;
    }

    public int getQuantity() {
        return quantity;
    }

    public Money getPrice() {
        return price;
    }

    public Tag getTag() {
        return tag;
    }

    public void setTag(Tag tag) {
        this.tag = tag;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof LineItem object) {
            return Objects.equals(this.name, object.name) && this.quantity == object.quantity
                    && Objects.equals(this.price, object) && Objects.equals(this.tag, object.tag);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, price, quantity, tag);
    }
}