package use_case.generate_report;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import java.util.Map;
import use_case.generate_report.grouping.ReportGrouping;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GenerateReportInteractorTest {

    @Test
    void requestsTransactionsForTheSelectedPeriod() {
        ReportPeriod selectedPeriod =
                ReportPeriod.daily(LocalDate.of(2026, 11, 23));

        List<ReportPeriod> requestedPeriods = new ArrayList<>();
        GenerateReportDataAccessInterface repository = period -> {
            requestedPeriods.add(period);
            return List.of();
        };

        GenerateReportOutputBoundary presenter = report -> { };
        ReportGrouping grouping = (period, transactions) ->
                new GenerateReportOutputData(period, Map.of(), 0L, 0L);
        GenerateReportInteractor interactor =
                new GenerateReportInteractor(repository, presenter, grouping, grouping);

        interactor.execute(new GenerateReportInputData(
                selectedPeriod, GenerateReportInputData.Grouping.TAG));

        assertEquals(List.of(selectedPeriod), requestedPeriods);
    }
}