package use_case.generate_report;

import entity.Transaction;
import java.util.List;

public interface GenerateReportDataAccessInterface {
    List<Transaction> findByPeriod(ReportPeriod period);
}