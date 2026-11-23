package entity;

import java.util.Set;
import org.junit.jupiter.api.Test;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ReportTest {

    @Test
    void purchaseWithTwoTagsCountsOnceInTotalSpending() {
        Report report = new Report();
        report.addPurchase(12_000L, Set.of("Shopping", "Weekly run"));

        assertEquals(12_000L, report.getTotalSpentCents());
        assertEquals(12_000L, report.getTotalForTag("Shopping"));
        assertEquals(12_000L, report.getTotalForTag("Weekly run"));
    }

    @Test
    void onlyPurchasesWithoutTagsIncreaseUntaggedSpending() {
        Report report = new Report();

        report.addPurchase(1_200L, Set.of());
        report.addPurchase(800L, Set.of("Groceries"));

        assertEquals(2_000L, report.getTotalSpentCents());
        assertEquals(1_200L, report.getUntaggedCents());
        assertEquals(800L, report.getTotalForTag("Groceries"));
    }

    @Test
    void twoPurchasesWithSameTagAccumulate() {
        Report report = new Report();

        report.addPurchase(1_200L, Set.of("Groceries"));
        report.addPurchase(800L, Set.of("Groceries"));

        assertEquals(2_000L, report.getTotalForTag("Groceries"));
        assertEquals(2_000L, report.getTotalSpentCents());
    }

    @Test
    void itemTagsCountOnlyAmountsNotAlreadyCoveredByPurchaseTags() {
        Report report = new Report();

        report.addPurchase(12_000L, Set.of("Shopping"),
                Map.of("Groceries", 1_100L, "Shopping", 500L));

        assertEquals(12_000L, report.getTotalSpentCents());
        assertEquals(12_000L, report.getTotalForTag("Shopping"));
        assertEquals(1_100L, report.getTotalForTag("Groceries"));
        assertEquals(0L, report.getUntaggedCents());
    }

    @Test
    void purchaseWithoutTagsLeavesOnlyTheUnassignedAmountUntagged() {
        Report report = new Report();

        report.addPurchase(12_000L, Set.of(), Map.of("Groceries", 1_100L));

        assertEquals(12_000L, report.getTotalSpentCents());
        assertEquals(1_100L, report.getTotalForTag("Groceries"));
        assertEquals(10_900L, report.getUntaggedCents());
    }
}