package use_case.generate_report;

import entity.Transaction;
import java.time.YearMonth;
import java.util.List;

/** Provides the transactions needed to generate a monthly report. */
public interface GenerateReportDataAccessInterface {

    /**
     * Returns all transactions dated within the requested month.
     * Returns an empty list when the month has no transactions.
     *
     * @param month the month to look up
     * @return transactions from that month
     */
    List<Transaction> findByMonth(YearMonth month);
}