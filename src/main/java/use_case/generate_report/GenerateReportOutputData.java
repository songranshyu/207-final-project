package use_case.generate_report;

import java.time.YearMonth;
import java.util.Map;

/** Spending totals for the selected month, in cents. */
public record GenerateReportOutputData(
        YearMonth month,
        GenerateReportInputData.Grouping grouping,
        Map<String, Long> totalsByGroup,
        long untaggedCents,
        long totalSpentCents) {

    public GenerateReportOutputData {
        totalsByGroup = Map.copyOf(totalsByGroup);
    }
}