package uk.infy.luna.functions;

import uk.infy.luna.Main;
import uk.infy.luna.gui.Auto_Updater;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.nio.file.attribute.BasicFileAttributes;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Objects;

public class AutoUpdater {
    private static final String JAR_URL = "http://applauncher.luna.infy.uk/Luna.App.Launcher.jar";
    private static final String JAR_NAME = "Luna.App.Launcher.jar";
    private static final int MAX_AGE_DAYS = 10;
    private static JFrame updateFrame;
    public static boolean checkForUpdates() {
        File jarFile = new File(JAR_NAME);
        if (jarFile.exists() && isOld(jarFile)) {
            SwingUtilities.invokeLater(() -> {
                showUpdateGUI();
                startUpdateProcess(jarFile);
            });
            return true;
        }
        return false;
    }
    private static boolean isOld(File file) {
        try {
            BasicFileAttributes attr = Files.readAttributes(file.toPath(), BasicFileAttributes.class);
            Instant lastModified = attr.lastModifiedTime().toInstant();
            Instant now = Instant.now();
            return ChronoUnit.DAYS.between(lastModified, now) >= MAX_AGE_DAYS;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
    private static void showUpdateGUI() {
        try {
            updateFrame = new JFrame("Updater");
            updateFrame.setPreferredSize(new Dimension(300, 150));
            updateFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            updateFrame.setContentPane(Auto_Updater.getUI());
            updateFrame.revalidate();
            updateFrame.repaint();
            updateFrame.setIconImage(new ImageIcon(Objects.requireNonNull(Main.class.getResource("/uk/infy/luna/resources/main.png"))).getImage());
            updateFrame.setLocationRelativeTo(null);
            updateFrame.pack();
            updateFrame.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private static void startUpdateProcess(File jarFile) {
        System.out.println("Starting update process...");
        SwingWorker<Void, Void> worker = new SwingWorker<Void, Void>() {
            @Override
            protected Void doInBackground() throws Exception {
                boolean success = downloadNewJar(jarFile);
                if (success) {
                    restartApplication();
                } else {
                    System.exit(31);
                }
                return null;
            }
            @Override
            protected void done() {
                updateFrame.dispose();
            }
        };
        worker.execute();
    }
    private static boolean downloadNewJar(File file) {
        try (InputStream in = new URL(JAR_URL).openStream()) {
            Files.copy(in, file.toPath(), StandardCopyOption.REPLACE_EXISTING);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
    private static void restartApplication() {
        String message;
        message = "Please Restart the app as it got updated.";
        SwingUtilities.invokeLater(() -> {
            JOptionPane.showMessageDialog(
                    null,
                    message,
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
            System.exit(0);
        });
    }
}
