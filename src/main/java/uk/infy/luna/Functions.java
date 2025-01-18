package uk.infy.luna;

import java.awt.*;
import java.net.URI;
import javax.swing.*;
import java.io.IOException;
import java.net.URL;

public class Functions {
    public static void browserurl(String url, JFrame parentFrame) {
        // example usage:
        // myDiscordServerButton.putClientProperty("url", "https://discord.gg/f5rkgMphd4");
        // myDiscordServerButton.addActionListener(openLinkAction);
        // where the button is myDiscordServerButton and i modify the url to the url i want
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

    public static JLabel luna(int width, int height, int x, int y) {
        // example usage
        // JLabel name = Functions.luna(100, 100, 50, 50);
        // panel2.add(name);
        // where (100, 100, 50, 50) would be width height x and y
        // replace name with anything as long as both have same one
        String imagePath = "/uk/infy/luna/main.png";
        URL imgUrl = Functions.class.getResource(imagePath);
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
//        Usage:
//        luna_launcher.addActionListener(e -> {
//           String command = "java -jar luna_launcher/luna_launcher.jar";
//           Functions.executeCommand(command);
//         });
//        replace luna_launcher with the button's name
//        and replace what's inside "" of String command with the command i want to run
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
}
