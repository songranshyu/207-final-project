package main;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

/**
 * Starts the app.
 *
 * <p>Placeholder so the project runs from day one. Member 2 replaces the body with
 * {@code AppBuilder}, which creates and wires every use case (the main component).
 */
public final class Main {

    private static final int WINDOW_WIDTH = 480;
    private static final int WINDOW_HEIGHT = 320;

    private Main() {
    }

    /**
     * Opens the main window.
     *
     * @param args unused
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            final JFrame frame = new JFrame("Bookkeeping App");
            frame.add(new JLabel("Setup works. Next: AppBuilder (Member 2).", SwingConstants.CENTER));
            frame.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }
}
