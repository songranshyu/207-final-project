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
}