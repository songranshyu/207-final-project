package use_case.sync_transactions;

import java.time.LocalDate;

public record BankTransaction(String id, LocalDate date, String storeName, long amountCents, String currency,
                              String category, boolean pending) {
    public BankTransaction {
        // Fill in restrictions later
    }
}
