package use_case.generate_report;

import java.time.YearMonth;

/** The month and grouping selected for a spending report. */
public record GenerateReportInputData(YearMonth month, Grouping grouping) {

    public enum Grouping {
        TAG,
        STORE
    }
}