package entity;

import java.time.YearMonth;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public final class MonthlyReport {
    private final YearMonth month;
    private final Map<String, Long> totalsByTag = new HashMap<>();
    private long totalSpentCents;
    private long untaggedCents;

    public MonthlyReport(YearMonth month) {
        this.month = month;
    }

    public void addPurchase(long amountCents, Set<String> tags) {
        totalSpentCents += amountCents;

        if (tags.isEmpty()) {
            untaggedCents += amountCents;
        }

        for (String tag : tags) {
            long previousTotal = totalsByTag.getOrDefault(tag, 0L);
            totalsByTag.put(tag, previousTotal + amountCents);
        }
    }

    public YearMonth getMonth() {
        return month;
    }

    public long getTotalSpentCents() {
        return totalSpentCents;
    }

    public long getUntaggedCents() {
        return untaggedCents;
    }

    public long getTotalForTag(String tag) {
        return totalsByTag.getOrDefault(tag, 0L);
    }
}