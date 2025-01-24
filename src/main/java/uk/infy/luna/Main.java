package uk.infy.luna;

import com.formdev.flatlaf.*;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;

import com.formdev.flatlaf.intellijthemes.*;
import com.formdev.flatlaf.intellijthemes.materialthemeuilite.*;
import uk.infy.luna.gui.Errors.Not_In_Empty_Folder;
import uk.infy.luna.gui.Main_GUI;
import uk.infy.luna.functions.functions;

public class Main {
    private static final Path themeFilePath = Path.of("./themes/theme.txt");
    static String themepath = "./themes/theme.txt";
    public static void main(String[] args) throws URISyntaxException {
        String[] requiredDirectories = {"luna_launcher", "logs", "sources", "themes"};
        for (String dirName : requiredDirectories) {
            File dir = new File(dirName);
            if (!dir.exists()) {
                if (dir.mkdirs()) {
                    System.out.println("Created directory: " + dir.getAbsolutePath());
                } else {
                    System.err.println("Failed to create directory: " + dir.getAbsolutePath());
                }
            }
        }
        try {
            File file = new File(themepath);
            if (!file.exists()) {
                if (file.createNewFile()) {
                    System.out.println("Theme file did not exist; creating it: " + file.getName());
                    try (FileWriter defaultTheme = new FileWriter(file)) {
                        defaultTheme.write("dracula");
                        System.out.println("Default theme 'dracula' written to the file.");
                    }
                } else {
                    System.err.println("Failed to create the theme file.");
                }
            } else {
                System.out.println("Theme file exists; not overwriting.");
            }
            String theme = Files.readString(themeFilePath).trim();
            if (theme.equalsIgnoreCase("dracula")) {
                FlatDarculaLaf.setup();
            } else if (theme.equalsIgnoreCase("light")) {
                FlatLightLaf.setup();
            } else if (theme.equalsIgnoreCase("dark")) {
                FlatDarkLaf.setup();
            } else if (theme.equalsIgnoreCase("dlight")) {
                FlatLightOwlIJTheme.setup();
            } else if (theme.equalsIgnoreCase("olight")) {
                FlatArcDarkOrangeIJTheme.setup();
            }
        } catch (IOException ex) {
            System.err.println("An error occurred.");
            ex.printStackTrace();
        }


        String[] allowedFilesAndFolders = {"testfile.txt", "luna_launcher", "logs", "sources", "themes"};
        File directory = new File(".");
        String appFileName = new File(Main.class.getProtectionDomain().getCodeSource().getLocation().toURI()).getName();
        List<String> allowedList = new ArrayList<>(List.of(allowedFilesAndFolders));
        allowedList.add(appFileName);
        List<String> unallowedFiles = functions.checkDirectory(directory, new HashSet<>(allowedList));

        if (!unallowedFiles.isEmpty()) {
            Not_In_Empty_Folder.showError(unallowedFiles);
            return;
        }



        SwingUtilities.invokeLater(Main::run);
    }

    private static void run() {
        try {
            Main_GUI mainApp = new Main_GUI();
            JFrame frame = new JFrame("Luna App Launcher");
            frame.setContentPane(mainApp.getTabbedPane());
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setPreferredSize(new Dimension(800, 600));
            frame.setIconImage(new ImageIcon(Objects.requireNonNull(Main.class.getResource("/uk/infy/luna/resources/main.png"))).getImage());
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
