package use_case.generate_report.grouping;

import entity.Transaction;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import use_case.generate_report.GenerateReportOutputData;
import use_case.generate_report.ReportPeriod;

public final class GroupByStore implements ReportGrouping {

    @Override
    public GenerateReportOutputData buildReport(
            ReportPeriod period, List<Transaction> transactions) {
        Map<String, Long> totalsByStore = new LinkedHashMap<>();
        long totalSpentCents = 0L;

        for (Transaction transaction : transactions) {
            long amountCents = transaction.getTotal().getCents();
            if (amountCents > 0) {
                totalsByStore.merge(transaction.getStoreName(), amountCents, Long::sum);
                totalSpentCents += amountCents;
            }
        }

        return new GenerateReportOutputData(
                period, Map.copyOf(totalsByStore), totalSpentCents, 0L);
    }
}