package use_case.generate_report;

public record GenerateReportInputData(ReportPeriod period, Grouping grouping) {
    public enum Grouping {
        TAG,
        STORE
    }
}