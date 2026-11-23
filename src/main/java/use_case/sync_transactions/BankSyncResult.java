package use_case.sync_transactions;

import java.util.List;

public record BankSyncResult(List<BankTransaction> added, List<BankTransaction> modified, List<String> removedIds,
                             String nextCursor) {

    public BankSyncResult {
        // Fill in restrictions later
    }
}
