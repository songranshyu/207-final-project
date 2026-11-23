package use_case.generate_report.grouping;

import entity.Report;
import entity.Tag;
import entity.Transaction;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import use_case.generate_report.GenerateReportOutputData;
import use_case.generate_report.ReportPeriod;

public final class GroupByTag implements ReportGrouping {

    @Override
    public GenerateReportOutputData buildReport(
            ReportPeriod period, List<Transaction> transactions) {
        Report report = new Report();

        for (Transaction transaction : transactions) {
            long amountCents = transaction.getTotal().getCents();
            if (amountCents <= 0) {
                continue;
            }

            Set<String> tagNames = new HashSet<>();
            for (Tag tag : transaction.getTags()) {
                tagNames.add(tag.getName());
            }
            report.addPurchase(amountCents, tagNames);
        }

        return new GenerateReportOutputData(
                period,
                report.getTotalsByTag(),
                report.getTotalSpentCents(),
                report.getUntaggedCents());
    }
}