package use_case.sync_transactions;

import java.util.List;

/**
 * Everything that changed at the bank since the last sync.
 *
 * @param added transactions that are new since the last sync
 * @param modified transactions whose details (amount, date or store) changed since the last sync
 * @param removedIds ids of transactions the bank no longer has
 * @param nextCursor where this sync ended; save it only after all of the above has been applied
 */
public record BankSyncResult(List<BankTransaction> added, List<BankTransaction> modified, List<String> removedIds,
                             String nextCursor) {
}
