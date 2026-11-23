package entity;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/** Tracks total spending and spending under each purchase tag. */
public final class Report {
    private long totalSpentCents;
    private long untaggedCents;
    private final Map<String, Long> totalsByTag = new HashMap<>();
    public long getUntaggedCents() {
        return untaggedCents;
    }
    public void addPurchase(long amountCents, Set<String> tags) {
        addPurchase(amountCents, tags, Map.of());
    }

    public void addPurchase(long amountCents, Set<String> tags,
                            Map<String, Long> itemTotalsByTag) {
        totalSpentCents += amountCents;

        for (String tag : tags) {
            totalsByTag.put(tag, getTotalForTag(tag) + amountCents);
        }

        long taggedItemCents = 0L;
        for (Map.Entry<String, Long> entry : itemTotalsByTag.entrySet()) {
            String itemTag = entry.getKey();
            long itemCents = entry.getValue();
            taggedItemCents += itemCents;

            if (!tags.contains(itemTag)) {
                totalsByTag.put(itemTag, getTotalForTag(itemTag) + itemCents);
            }
        }

        if (tags.isEmpty()) {
            untaggedCents += amountCents - taggedItemCents;
        }
    }

    public long getTotalSpentCents() {
        return totalSpentCents;
    }

    public long getTotalForTag(String tag) {
        return totalsByTag.getOrDefault(tag, 0L);
    }
}