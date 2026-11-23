package use_case.generate_report;

import entity.Transaction;
import java.util.List;
import use_case.generate_report.grouping.ReportGrouping;

public final class GenerateReportInteractor implements GenerateReportInputBoundary {
    private final GenerateReportDataAccessInterface repository;
    private final GenerateReportOutputBoundary presenter;
    private final ReportGrouping groupByTag;
    private final ReportGrouping groupByStore;

    public GenerateReportInteractor(
            GenerateReportDataAccessInterface repository,
            GenerateReportOutputBoundary presenter,
            ReportGrouping groupByTag,
            ReportGrouping groupByStore) {
        this.repository = repository;
        this.presenter = presenter;
        this.groupByTag = groupByTag;
        this.groupByStore = groupByStore;
    }

    @Override
    public void execute(GenerateReportInputData inputData) {
        ReportPeriod period = inputData.period();
        List<Transaction> transactions = repository.findByPeriod(period);

        ReportGrouping grouping = switch (inputData.grouping()) {
            case TAG -> groupByTag;
            case STORE -> groupByStore;
        };

        presenter.present(grouping.buildReport(period, transactions));
    }
}