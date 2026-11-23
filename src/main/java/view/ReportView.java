package view;

import interface_adapter.generate_report.GenerateReportController;
import java.awt.BorderLayout;
import java.time.YearMonth;
import java.time.format.DateTimeParseException;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import use_case.generate_report.GenerateReportInputData.Grouping;

/** Lets the user request a monthly spending report. */
public final class ReportView extends JPanel {

    public ReportView(GenerateReportController controller) {
        super(new BorderLayout());

        JTextField monthField = new JTextField(YearMonth.now().toString(), 7);
        JComboBox<Grouping> groupingBox = new JComboBox<>(Grouping.values());
        JButton generateButton = new JButton("Generate report");
        JLabel message = new JLabel(" ");

        JPanel controls = new JPanel();
        controls.add(new JLabel("Month (YYYY-MM)"));
        controls.add(monthField);
        controls.add(new JLabel("Group by"));
        controls.add(groupingBox);
        controls.add(generateButton);

        add(new JLabel("Monthly spending report"), BorderLayout.NORTH);
        add(controls, BorderLayout.CENTER);
        add(message, BorderLayout.SOUTH);

        generateButton.addActionListener(event -> {
            YearMonth month;
            try {
                month = YearMonth.parse(monthField.getText().trim());
            } catch (DateTimeParseException exception) {
                message.setText("Enter a month like 2026-11.");
                return;
            }

            message.setText(" ");
            Grouping grouping = groupingBox.getItemAt(groupingBox.getSelectedIndex());
            controller.generateReport(month, grouping);
        });
    }
}