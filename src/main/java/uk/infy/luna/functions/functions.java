package uk.infy.luna.functions;

import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URI;
import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.io.FileInputStream;

import com.formdev.flatlaf.*;
import com.formdev.flatlaf.intellijthemes.*;
import com.formdev.flatlaf.intellijthemes.materialthemeuilite.*;

public class functions {
    public static void browserurl(String url, JFrame parentFrame) {
        /*
         example usage:
         myDiscordServerButton.putClientProperty("url", "https://discord.gg/f5rkgMphd4");
         myDiscordServerButton.addActionListener(openLinkAction);
         where the button is myDiscordServerButton and i modify the url to the url i want
        */
        try {
            if (Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.BROWSE)) {
                Desktop.getDesktop().browse(new URI(url));
            } else {
                JOptionPane.showMessageDialog(parentFrame,
                        "Opening links is not supported on your system.",
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(parentFrame,
                    "Failed to open the link. Please check your default browser setup.",
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    public static  JLabel luna(int width, int height, int x, int y) {
        /*
         example usage
         JLabel name = functions.luna(100, 100, 50, 50);
         panel2.add(name);
         where (100, 100, 50, 50) would be width height x and y
         replace name with anything as long as both have same one
        */
        String imagePath = "/uk/infy/luna/resources/main.png";
        URL imgUrl = functions.class.getResource(imagePath);
        if (imgUrl == null) {
            throw new IllegalArgumentException("Image not found: " + imagePath);
        }
        ImageIcon icon = new ImageIcon(imgUrl);
        Image image = icon.getImage();
        Image resizedImage = image.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        ImageIcon resizedIcon = new ImageIcon(resizedImage);
        JLabel imageLabel = new JLabel(resizedIcon);
        imageLabel.setBounds(x, y, width, height);
        return imageLabel;
    }
    public static void executeCommand(String command) {
/*
        Usage:
        luna_launcher.addActionListener(e -> {
           String command = "java -jar luna_launcher/luna_launcher.jar";
           Functions.executeCommand(command);
         });
        replace luna_launcher with the button's name
        and replace what's inside "" of String command with the command i want to run
*/
        try {
            Process process = Runtime.getRuntime().exec(command);
            int exitCode = process.waitFor();

            if (exitCode != 0) {
                System.err.println("Failed to execute the command: " + command);
            }
        } catch (IOException | InterruptedException e) {
            System.err.println("Error while executing the command: " + e.getMessage());
        }
    }
    public static java.util.List<String> checkDirectory(File directory, HashSet<String> allowedFilesAndFolders) {
/*
        List<String> unallowedFiles = functions.checkDirectory(directory, new HashSet<>(allowedList));
        Line 33 in Main.java
*/
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
    public static void downloadFile(String fileUrl, String destinationFile) throws IOException {
/*
        import uk.infy.luna.Functions.functions;
        luna_launcher.addActionListener(e -> {
            String filePath = "./luna_launcher/luna_launcher.jar"; This is where it should be downloaded
            File file = new File(filePath);
            if (file.exists()) {
                String command = "java -jar " + filePath; Command 2 run
                functions.executeCommand(command); Run command if file exist
            } else {
                try {
                    String fileUrl = "https://github.com/Hussein-Playz/Luna-Launcher/releases/download/Release/Luna-Launcher-3.4.38.1.jar"; This would be the file i want to download
                    functions.downloadFile(fileUrl, filePath); Downloads it straight to the area i want it to be
                    String command = "java -jar " + filePath; Command to run after download
                    functions.executeCommand(command); Run that command
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(null, "Failed to download the file: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE); Error if failed
                }
            }
        });
*/
        URL url = new URL(fileUrl);
        URLConnection connection = url.openConnection();
        try (InputStream inputStream = connection.getInputStream();
             FileOutputStream outputStream = new FileOutputStream(destinationFile)) {
            byte[] buffer = new byte[4096];
            int bytesRead;
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }
        }
    }
    public static void extractZipFile(String zipFilePath, String destinationDirectory) throws IOException {
        File destDir = new File(destinationDirectory);
        if (!destDir.exists()) {
            destDir.mkdirs();
        }

        try (ZipInputStream zipInputStream = new ZipInputStream(new FileInputStream(zipFilePath))) {
            ZipEntry entry;
            while ((entry = zipInputStream.getNextEntry()) != null) {
                File entryFile = new File(destinationDirectory, entry.getName());
                if (entry.isDirectory()) {
                    entryFile.mkdirs();
                } else {
                    File parent = entryFile.getParentFile();
                    if (!parent.exists()) {
                        parent.mkdirs();
                    }
                    try (FileOutputStream outputStream = new FileOutputStream(entryFile)) {
                        byte[] buffer = new byte[1024];
                        int len;
                        while ((len = zipInputStream.read(buffer)) > 0) {
                            outputStream.write(buffer, 0, len);
                        }
                    }
                }
            }
        }
    }
    public static void openSourceCodeViewer(String sourceDirectory) {
/*
        Usage is the same as download one but instead we have some extra stuff
        luna_launcher_source_code.addActionListener(e -> {
            String filePathZip = "./sources/luna_launcher.zip"; setting up download stuff
            String filePath = "./sources/luna_launcher"; setting up Paths
            File file = new File(filePath); setting up download stuff
            if (file.exists()) { if the file exists then LAUNCH THIS CODE VIEWER at filePath aka ./sources/luna_launcher this case
                functions.openSourceCodeViewer(filePath);
            } else {
                try {
                    String fileUrl = "https://github.com/Hussein-Playz/Luna-Launcher/archive/refs/heads/master.zip"; setting up download stuff
                    functions.downloadFile(fileUrl, filePathZip); setting up download stuff
                    functions.extractZipFile(filePathZip, filePath); setting up download stuff
                    functions.openSourceCodeViewer(filePath); Launch the code viewer at filePath aka ./sources/luna_launcher in this case
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(null, "Failed to download the file: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE); error if failed
                }
            }
        });
*/
        File rootDir = new File(sourceDirectory);

        if (!rootDir.exists() || !rootDir.isDirectory()) {
            JOptionPane.showMessageDialog(null, "The specified directory does not exist or is not a directory: " + sourceDirectory, "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        JFrame viewerFrame = new JFrame("Source Code Viewer");
        viewerFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        viewerFrame.setSize(800, 600);
        DefaultMutableTreeNode rootNode = new DefaultMutableTreeNode(rootDir.getName());
        createNodes(rootNode, rootDir);
        JScrollPane treeScrollPane = getJScrollPane(rootNode, viewerFrame);
        viewerFrame.add(treeScrollPane);
        viewerFrame.setVisible(true);
    }
    private static JScrollPane getJScrollPane(DefaultMutableTreeNode rootNode, JFrame viewerFrame) {
        JTree fileTree = new JTree(rootNode);
        fileTree.setRootVisible(true);
        fileTree.addTreeSelectionListener(event -> {
            DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) fileTree.getLastSelectedPathComponent();
            if (selectedNode == null) return;

            File selectedFile = (File) selectedNode.getUserObject();
            if (selectedFile.isFile()) {
                try {
                    Desktop.getDesktop().open(selectedFile);
                } catch (IOException e) {
                    JOptionPane.showMessageDialog(viewerFrame, "Cannot open file: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        return new JScrollPane(fileTree);
    }
    private static void createNodes(DefaultMutableTreeNode parentNode, File file) {
        File[] files = file.listFiles();
        if (files != null) {
            for (File child : files) {
                DefaultMutableTreeNode childNode = new DefaultMutableTreeNode(child.getName());
                childNode.setUserObject(child);

                parentNode.add(childNode);

                if (child.isDirectory()) {
                    createNodes(childNode, child);
                }
            }
        }
    }
    public static void addlunaimage(JButton button) {
/*
        functions.addlunaimage(luna_launcher);
        functions.addlunaimage(luna_launcher_source_code);
        functions.addlunaimage(app_launcher_source_code);
        functions.addlunaimage(website_source_code);
        functions.addlunaimage(myWebsiteButton);
        functions.addlunaimage(website1);
        functions.addlunaimage(website2);
        functions.addlunaimage(website3);
        functions.addlunaimage(website4);
        just call the function and in the () put your button's name THAT'S HOW FUCKING EASY IT IS
*/
        try {
            URL imageUrl = functions.class.getClassLoader().getResource("uk/infy/luna/resources/main.png");
            if (imageUrl != null) {
                ImageIcon originalIcon = new ImageIcon(imageUrl);
                button.addComponentListener(new ComponentAdapter() {
                    @Override
                    public void componentResized(ComponentEvent e) {
                        if (button.getIcon() == null) {
                            int width = button.getWidth();
                            int height = button.getHeight();
                            if (width > 0 && height > 0) {
                                Image originalImage = originalIcon.getImage();
                                Image scaledImage = originalImage.getScaledInstance(width, height, Image.SCALE_SMOOTH);
                                button.setIcon(new ImageIcon(scaledImage));
                                button.setHorizontalTextPosition(SwingConstants.CENTER);
                                button.setVerticalTextPosition(SwingConstants.CENTER);
                                button.setIconTextGap(0);
                            }
                        }
                    }
                });
                button.setContentAreaFilled(false);
                button.setBorderPainted(false);
                button.setFocusPainted(false);
            } else {
                JOptionPane.showMessageDialog(null, "Image not found.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error loading button image: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    public static void setTheme(JButton button, String themeName) {
        /*
         Usage example:
         functions.setTheme(myButton, "dracula");
         Replace myButton with your button name and "dracula" with the theme name.
        */
        button.addActionListener(e -> {
            Path themeFilePath = Path.of("./themes/theme.txt");
            try {
                Files.createDirectories(themeFilePath.getParent());
                Files.writeString(themeFilePath, themeName, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
                System.out.println("Theme updated to: " + themeName);
                switch (themeName.toLowerCase()) {
                    case "light":
                        FlatLightLaf.setup();
                        break;
                    case "dark":
                        FlatDarkLaf.setup();
                        break;
                    case "dlight":
                        FlatLightOwlIJTheme.setup();
                        break;
                    case "olight":
                        FlatArcDarkOrangeIJTheme.setup();
                        break;
                    case "dracula":
                    default:
                        FlatDarculaLaf.setup();
                        break;
                }
                SwingUtilities.updateComponentTreeUI(button.getTopLevelAncestor());

            } catch (IOException ex) {
                System.err.println("Failed to update theme file: " + ex.getMessage());
                ex.printStackTrace();
            }
        });
    }
    public static void DeleteStuff(File file) {
        /*
                String filepath = "C:\\GFG"; store file path
                File file = new File(filepath);
                DeleteStuff(file); call DeleteStuff function to delete subdirectory and files
                file.delete(); delete main GFG folder
        */
        for (File subfile : Objects.requireNonNull(file.listFiles())) {
            if (subfile.isDirectory()) {
                DeleteStuff(subfile);
            }
            subfile.delete();
        }
    }
//    luna is a placeholder for now :) not sure when i'll use it but it will be sometime
}
