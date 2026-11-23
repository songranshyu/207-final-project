package use_case.generate_report.grouping;

import entity.Transaction;
import java.time.YearMonth;
import java.util.List;
import use_case.generate_report.GenerateReportOutputData;

/** Builds a spending report from one month's transactions. */
public interface ReportGrouping {

    /**
     * Counts outgoing purchases and excludes refunds and deposits.
     * An empty month produces an empty report with zero totals.
     */
    GenerateReportOutputData buildReport(YearMonth month, List<Transaction> transactions);
}