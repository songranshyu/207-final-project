package entity;

import java.math.BigDecimal;
import java.util.Currency;
import java.util.Locale;
import java.util.Objects;

/** An immutable monetary amount stored as integer cents, never as a double. */
public final class Money {
    private final long cents;
    private final String currency;

    public Money(long cents, String currency) {
        Objects.requireNonNull(currency, "Currency cannot be null");
        this.currency = Currency.getInstance(
                currency.trim().toUpperCase(Locale.ROOT)).getCurrencyCode();
        this.cents = cents;
    }

    public long getCents() {
        return cents;
    }

    public String getCurrency() {
        return currency;
    }

    public Money add(Money other) {
        checkCurrency(other);
        return new Money(Math.addExact(cents, other.cents), currency);
    }

    public Money subtract(Money other) {
        checkCurrency(other);
        return new Money(Math.subtractExact(cents, other.cents), currency);
    }

    private void checkCurrency(Money other) {
        Objects.requireNonNull(other, "Money cannot be null");
        if (!currency.equals(other.currency)) {
            throw new IllegalArgumentException(
                    "Cannot combine different currencies");
        }
    }

    @Override
    public String toString() {
        String amount = BigDecimal.valueOf(cents, 2).abs().toPlainString();
        String symbol = "CAD".equals(currency) ? "$" : currency + " ";
        return (cents < 0 ? "-" : "") + symbol + amount;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (!(object instanceof Money)) {
            return false;
        }
        Money that = (Money) object;
        return cents == that.cents && currency.equals(that.currency);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cents, currency);
    }
}
