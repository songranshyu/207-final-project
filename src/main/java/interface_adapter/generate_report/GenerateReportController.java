package interface_adapter.generate_report;

import java.time.YearMonth;
import use_case.generate_report.GenerateReportInputBoundary;
import use_case.generate_report.GenerateReportInputData;

/** Sends the user's report request to the use case. */
public final class GenerateReportController {
    private final GenerateReportInputBoundary inputBoundary;

    public GenerateReportController(GenerateReportInputBoundary inputBoundary) {
        this.inputBoundary = inputBoundary;
    }

    public void generateReport(YearMonth month, GenerateReportInputData.Grouping grouping) {
        GenerateReportInputData request = new GenerateReportInputData(month, grouping);
        inputBoundary.execute(request);
    }
}