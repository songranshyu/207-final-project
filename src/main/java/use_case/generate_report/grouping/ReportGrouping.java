package use_case.generate_report.grouping;

import entity.Transaction;
import java.util.List;
import use_case.generate_report.GenerateReportOutputData;
import use_case.generate_report.ReportPeriod;

public interface ReportGrouping {
    GenerateReportOutputData buildReport(
            ReportPeriod period, List<Transaction> transactions);
}