package use_case.sync_transactions;

import java.time.LocalDate;

/**
 * One transaction as the bank reports it, before it becomes one of our {@code Transaction} entities.
 *
 * @param id the bank's id for this transaction; used to spot duplicates and to apply later changes or removals
 * @param date the day the transaction happened
 * @param storeName where the money was spent, or the bank's description if there is no store
 * @param amountCents the amount in cents; positive means money went out (a purchase), negative means money came
 *     in (a refund or deposit)
 * @param currency the currency code, e.g. "CAD"
 * @param category the bank's spending category code, e.g. "FOOD_AND_DRINK", or null if the bank gave none
 * @param pending true if the transaction hasn't settled yet (we skip these)
 */
public record BankTransaction(String id, LocalDate date, String storeName, long amountCents, String currency,
                              String category, boolean pending) {
}
