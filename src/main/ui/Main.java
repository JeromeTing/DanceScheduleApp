package ui;

import static ui.Table.renderAndDisplayGUI;

public class Main {
    public static void main(String[] args) {
        //new DanceScheduleApp();
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                renderAndDisplayGUI();
            }
        });
    }
}
