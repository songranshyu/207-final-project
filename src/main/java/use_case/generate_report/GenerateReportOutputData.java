package use_case.generate_report;

import java.util.Map;

public record GenerateReportOutputData(
        ReportPeriod period,
        Map<String, Long> groupTotalsCents,
        long totalSpentCents,
        long untaggedCents) {
}