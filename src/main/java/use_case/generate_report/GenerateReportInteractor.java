package use_case.generate_report;

import entity.Transaction;
import java.util.List;
import use_case.generate_report.grouping.ReportGrouping;

/** Generates the requested monthly spending report. */
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
        List<Transaction> transactions = repository.findByMonth(inputData.month());

        ReportGrouping grouping = switch (inputData.grouping()) {
            case TAG -> groupByTag;
            case STORE -> groupByStore;
        };

        GenerateReportOutputData report = grouping.buildReport(inputData.month(), transactions);
        presenter.present(report);
    }
}