package use_case.generate_report;

/** Receives the completed monthly report. */
public interface GenerateReportOutputBoundary {
    void present(GenerateReportOutputData outputData);
}