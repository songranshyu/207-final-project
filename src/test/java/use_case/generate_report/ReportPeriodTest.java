package use_case.generate_report;

import java.time.LocalDate;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ReportPeriodTest {

    @Test
    void dailyReportIncludesOnlyTheSelectedDate() {
        LocalDate selectedDate = LocalDate.of(2026, 11, 23);
        ReportPeriod period = ReportPeriod.daily(selectedDate);

        assertFalse(period.contains(selectedDate.minusDays(1)));
        assertTrue(period.contains(selectedDate));
        assertFalse(period.contains(selectedDate.plusDays(1)));
    }

    @Test
    void monthlyReportIncludesOnlyDatesInTheSelectedMonth() {
        LocalDate selectedDate = LocalDate.of(2024, 2, 15);
        ReportPeriod period = ReportPeriod.monthly(selectedDate);

        assertFalse(period.contains(LocalDate.of(2024, 1, 31)));
        assertTrue(period.contains(LocalDate.of(2024, 2, 1)));
        assertTrue(period.contains(LocalDate.of(2024, 2, 29)));
        assertFalse(period.contains(LocalDate.of(2024, 3, 1)));
    }

    @Test
    void weeklyReportIncludesMondayThroughSundayAcrossNewYear() {
        LocalDate selectedDate = LocalDate.of(2025, 1, 1); // Wednesday
        ReportPeriod period = ReportPeriod.weekly(selectedDate);

        assertFalse(period.contains(LocalDate.of(2024, 12, 29)));
        assertTrue(period.contains(LocalDate.of(2024, 12, 30))); // Monday
        assertTrue(period.contains(selectedDate));
        assertTrue(period.contains(LocalDate.of(2025, 1, 5)));   // Sunday
        assertFalse(period.contains(LocalDate.of(2025, 1, 6)));
    }
}