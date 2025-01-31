package uk.infy.luna.gui;

import javax.swing.*;
import java.awt.*;

public class Auto_Updater {
    public static JPanel getUI() {
        JLabel statusLabel = new JLabel("Updating application...", SwingConstants.CENTER);
        JProgressBar progressBar = new JProgressBar();
        progressBar.setIndeterminate(true);
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.add(statusLabel, BorderLayout.CENTER);
        panel.add(progressBar, BorderLayout.SOUTH);
        return panel;
    }
}
