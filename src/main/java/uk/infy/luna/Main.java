package uk.infy.luna;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import static javax.swing.UIManager.setLookAndFeel;

public class Main {
    public static void main(String[] args) throws UnsupportedLookAndFeelException, ClassNotFoundException, InstantiationException, IllegalAccessException, URISyntaxException {
        for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
            if ("com.sun.java.swing.plaf.gtk.GTKLookAndFeel".equals(info.getClassName())) {
                setLookAndFeel(info.getClassName());
                break;
            }
        }
        File directory = new File(".");
        String[] allowedFilesAndFolders = {
                "testfile.txt", "luna_launcher"
        };
        String appFileName = new File(Main.class.getProtectionDomain().getCodeSource().getLocation().toURI()).getName();
        List<String> allowedList = new ArrayList<>(List.of(allowedFilesAndFolders));
        allowedList.add(appFileName);
        List<String> unallowedFiles = checkDirectory(directory, new HashSet<>(allowedList));
        if (!unallowedFiles.isEmpty()) {
            nofiles.showError(unallowedFiles);
            return;
        }
        SwingUtilities.invokeLater(() -> {
            try {
                Mains mainApp = new Mains();
                JFrame frame = new JFrame("Luna App Launcher");
                frame.setContentPane(mainApp.getTabbedPane());
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setPreferredSize(new Dimension(800, 600));
                frame.setIconImage(new ImageIcon(Main.class.getResource("/uk/infy/luna/main.png")).getImage());
                frame.pack();
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    private static List<String> checkDirectory(File directory, HashSet<String> allowedFilesAndFolders) {
        List<String> unallowedFiles = new ArrayList<>();

        if (!directory.isDirectory()) {
            unallowedFiles.add("Error: The specified path is not a directory.");
            return unallowedFiles;
        }

        File[] entries = directory.listFiles();
        if (entries == null) {
            unallowedFiles.add("Error: Unable to read the directory contents.");
            return unallowedFiles;
        }

        for (File entry : entries) {
            String name = entry.getName();

            if (!allowedFilesAndFolders.contains(name)) {
                unallowedFiles.add(name);
            }
        }

        return unallowedFiles;
    }
}
