package entity;

import java.time.YearMonth;
import java.util.Set;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MonthlyReportTest {

    @Test
    void purchaseWithTwoTagsCountsOnceInGrandTotal() {
        MonthlyReport report = new MonthlyReport(YearMonth.of(2026, 11));

        report.addPurchase(12_000L, Set.of("Shopping", "Weekly run"));

        assertEquals(12_000L, report.getTotalSpentCents());
        assertEquals(12_000L, report.getTotalForTag("Shopping"));
        assertEquals(12_000L, report.getTotalForTag("Weekly run"));
    }

    @Test
    void twoPurchasesWithSameTagAccumulate() {
        MonthlyReport report = new MonthlyReport(YearMonth.of(2026, 11));

        report.addPurchase(1_200L, Set.of("Groceries"));
        report.addPurchase(800L, Set.of("Groceries"));

        assertEquals(2_000L, report.getTotalSpentCents());
        assertEquals(2_000L, report.getTotalForTag("Groceries"));
    }

    @Test
    void purchaseWithNoTagsCountsAsUntagged() {
        MonthlyReport report = new MonthlyReport(YearMonth.of(2026, 11));

        report.addPurchase(1_200L, Set.of());

        assertEquals(1_200L, report.getTotalSpentCents());
        assertEquals(1_200L, report.getUntaggedCents());
    }
}

