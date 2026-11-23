package use_case.generate_report;

/** Accepts a request to generate a monthly spending report. */
public interface GenerateReportInputBoundary {
    void execute(GenerateReportInputData inputData);
}