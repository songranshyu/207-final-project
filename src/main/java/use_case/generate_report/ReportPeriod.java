package use_case.generate_report;
import java.time.LocalDate;
import java.time.DayOfWeek;
import java.time.temporal.TemporalAdjusters;

/** A reporting period that includes both its start and end dates. */
public final class ReportPeriod {
    private final LocalDate startDate;
    private final LocalDate endDate;

    private ReportPeriod(LocalDate startDate, LocalDate endDate) {
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public static ReportPeriod daily(LocalDate selectedDate) {
        return new ReportPeriod(selectedDate, selectedDate);
    }

    public static ReportPeriod monthly(LocalDate selectedDate) {
        LocalDate startDate = selectedDate.withDayOfMonth(1);
        LocalDate endDate = selectedDate.withDayOfMonth(selectedDate.lengthOfMonth());
        return new ReportPeriod(startDate, endDate);
    }

    public static ReportPeriod weekly(LocalDate selectedDate) {
        LocalDate startDate = selectedDate.with(
                TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY));
        LocalDate endDate = startDate.plusDays(6);
        return new ReportPeriod(startDate, endDate);
    }

    public boolean contains(LocalDate date) {
        return !date.isBefore(startDate) && !date.isAfter(endDate);
    }
}