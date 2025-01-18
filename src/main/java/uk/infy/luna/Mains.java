package uk.infy.luna;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.*;
import java.nio.file.*;
import java.net.URI;
import uk.infy.luna.Functions;

public class Mains extends JFrame {

    private JTabbedPane tabbedPane1;
    private JButton myDiscordServerButton;
    private JButton myWebsiteButton;
    private JButton website1;
    private JButton website2;
    private JButton website3;
    private JButton luna_launcher;

    public JTabbedPane getTabbedPane() {
        return tabbedPane1;
    }

    public Mains() {
        ActionListener openLinkAction = e -> {
            JButton sourceButton = (JButton) e.getSource();
            String url = (String) sourceButton.getClientProperty("url");
            Functions.browserurl(url, this);
        };
        myDiscordServerButton.putClientProperty("url", "https://discord.gg/f5rkgMphd4");
        myDiscordServerButton.addActionListener(openLinkAction);
        myWebsiteButton.putClientProperty("url", "https://luna.infy.uk");
        myWebsiteButton.addActionListener(openLinkAction);
        website1.putClientProperty("url", "https://luna.infy.uk");
        website1.addActionListener(openLinkAction);
        website2.putClientProperty("url", "https://luna.infy.uk");
        website2.addActionListener(openLinkAction);
        website3.putClientProperty("url", "https://luna.infy.uk");
        website3.addActionListener(openLinkAction);
        luna_launcher.addActionListener(e -> {
            String filePath = "./luna_launcher/luna_launcher.jar";
            File file = new File(filePath);
            if (file.exists()) {
                String command = "java -jar " + filePath;
                Functions.executeCommand(command);
            } else {
                try {
                    String fileUrl = "https://github.com/Hussein-Playz/Luna-Launcher/releases/download/Release/Luna-Launcher-3.4.38.1.jar";
                    downloadFile(fileUrl, filePath);
                    String command = "java -jar " + filePath;
                    Functions.executeCommand(command);
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(null, "Failed to download the file: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }
    public static void downloadFile(String fileUrl, String destinationFile) throws IOException {
        URL url = new URL(fileUrl);
        URLConnection connection = url.openConnection();
        File dir = new File("./luna_launcher");
        if (!dir.exists()) {
            dir.mkdirs();
        }
        try (InputStream inputStream = connection.getInputStream();
             FileOutputStream outputStream = new FileOutputStream(destinationFile)) {
            byte[] buffer = new byte[4096];
            int bytesRead;
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }
        }
    }

    {
        $$$setupUI$$$();
    }

    private void $$$setupUI$$$() {
        final JPanel panel1 = new JPanel();
        panel1.setLayout(null);
        panel1.setMaximumSize(new Dimension(800, 600));
        panel1.setMinimumSize(new Dimension(800, 600));
        panel1.setPreferredSize(new Dimension(800, 600));
        tabbedPane1 = new JTabbedPane();
        tabbedPane1.setMaximumSize(new Dimension(800, 600));
        tabbedPane1.setMinimumSize(new Dimension(800, 600));
        tabbedPane1.setPreferredSize(new Dimension(800, 600));
        tabbedPane1.setBounds(0, 0, 800, 600);
        panel1.add(tabbedPane1);
        final JPanel panel2 = new JPanel();
        panel2.setLayout(null);
        panel2.setMaximumSize(new Dimension(900, 500));
        panel2.setMinimumSize(new Dimension(800, 453));
        panel2.setPreferredSize(new Dimension(800, 600));

        tabbedPane1.addTab("Why", panel2);

        panel2.setLayout(new BorderLayout());
        panel2.setMaximumSize(new Dimension(900, 500));
        panel2.setMinimumSize(new Dimension(800, 453));
        panel2.setPreferredSize(new Dimension(800, 600));
        final JPanel labelsPanel = new JPanel();
        labelsPanel.setLayout(new BoxLayout(labelsPanel, BoxLayout.Y_AXIS));
        labelsPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        final JLabel label1 = new JLabel("Why?");
        final JLabel label2 = new JLabel("1st, it's cool.");
        final JLabel label3 = new JLabel("2nd, You can launch all the apps I've ever created from here using the Launch");
        final JLabel label5 = new JLabel("Apps option.");
        final JLabel label4 = new JLabel("Lastly, I like to program. It's fun and gives me something to work on.");
        panel2.add(labelsPanel, BorderLayout.CENTER);
        myWebsiteButton = new JButton("Visit My Website");
        myWebsiteButton.setFont(new Font("Arial", Font.BOLD, 18));
        label1.setFont(new Font("Arial", Font.BOLD, 22));
        label2.setFont(new Font("Arial", Font.PLAIN, 18));
        label3.setFont(new Font("Arial", Font.PLAIN, 18));
        label5.setFont(new Font("Arial", Font.PLAIN, 18));
        label4.setFont(new Font("Arial", Font.PLAIN, 18));
        labelsPanel.add(label1);
        labelsPanel.add(label2);
        labelsPanel.add(label3);
        labelsPanel.add(label5);
        labelsPanel.add(label4);
        panel2.add(myWebsiteButton, BorderLayout.SOUTH);

        final JPanel panel3 = new JPanel();

        panel3.setLayout(new BorderLayout());
        panel3.setMaximumSize(new Dimension(800, Integer.MAX_VALUE));
        panel3.setMinimumSize(new Dimension(800, 469));
        panel3.setPreferredSize(new Dimension(800, 600));

        tabbedPane1.addTab("About Me", panel3);

        final JPanel labelsPanel1 = new JPanel();

        labelsPanel1.setLayout(new BoxLayout(labelsPanel1, BoxLayout.Y_AXIS));
        labelsPanel1.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        final JLabel title123 = new JLabel("About Me.");
        final JLabel label6 = new JLabel("Am a 13 year old who LOVES tech and so at a young age of 9 I");
        final JLabel label7 = new JLabel("think I began doing stuff with it after Windows 7 EOL was");
        final JLabel label8 = new JLabel("announced and I upgraded to Windows 10. I began my tech journey there");
        final JLabel label9 = new JLabel("Although, I was always techy way before that");
        website1 = new JButton("Visit My Website");
        website1.setFont(new Font("Arial", Font.BOLD, 18));
        panel3.add(website1, BorderLayout.SOUTH);
        title123.setFont(new Font("Arial", Font.BOLD, 22));
        label6.setFont(new Font("Arial", Font.PLAIN, 18));
        label7.setFont(new Font("Arial", Font.PLAIN, 18));
        label8.setFont(new Font("Arial", Font.PLAIN, 18));
        label9.setFont(new Font("Arial", Font.PLAIN, 18));
        labelsPanel1.add(title123);
        labelsPanel1.add(label6);
        labelsPanel1.add(label7);
        labelsPanel1.add(label8);
        labelsPanel1.add(label9);
        panel3.add(labelsPanel1, BorderLayout.CENTER);

        final JPanel panel4 = new JPanel();
        panel4.setLayout(new BorderLayout());
        panel4.setPreferredSize(new Dimension(800, 600));

        tabbedPane1.addTab("Contact Me", panel4);

        final JPanel labelsPanel3 = new JPanel();
        labelsPanel3.setLayout(new BoxLayout(labelsPanel3, BoxLayout.Y_AXIS));
        labelsPanel3.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        final JLabel label11 = new JLabel("Contact Me On Discord");
        myDiscordServerButton = new JButton("My Discord Server");
        label11.setFont(new Font("Arial", Font.BOLD, 18));
        myDiscordServerButton.setFont(new Font("Arial", Font.BOLD, 18));

        labelsPanel3.add(label11);
        labelsPanel3.add(myDiscordServerButton);
        panel4.add(labelsPanel3, BorderLayout.CENTER);

        website2 = new JButton("Visit My Website");
        website2.setFont(new Font("Arial", Font.BOLD, 18));
        panel4.add(website2, BorderLayout.SOUTH);
        final JPanel panel5 = new JPanel();
        panel5.setLayout(new GridBagLayout());
        panel5.setPreferredSize(new Dimension(800, 600));

        GridBagConstraints gbcLabel = new GridBagConstraints();
        gbcLabel.gridx = 0;
        gbcLabel.gridy = 0;
        gbcLabel.gridwidth = GridBagConstraints.REMAINDER;
        gbcLabel.fill = GridBagConstraints.HORIZONTAL;
        final JLabel launching = new JLabel("<html>WARNING: IF YOUR LAUNCHING A APP FOR THE FIRST TIME IT'LL HAVE TO DOWNLOAD<br>IT FIRST</html>");
        launching.setFont(new Font("Arial", Font.PLAIN, 18));
        panel5.add(launching, gbcLabel);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.anchor = GridBagConstraints.CENTER;

        luna_launcher = new JButton("Luna Launcher");
        luna_launcher.setFont(new Font("Arial", Font.BOLD, 36));
        luna_launcher.setPreferredSize(new Dimension(300, 100));
        panel5.add(luna_launcher, gbc);

        GridBagConstraints gbcWebsite3 = new GridBagConstraints();
        gbcWebsite3.gridx = 0;
        gbcWebsite3.gridy = 2;
        gbcWebsite3.gridwidth = GridBagConstraints.REMAINDER;
        gbcWebsite3.fill = GridBagConstraints.HORIZONTAL;
        website3 = new JButton("Visit My Website");
        website3.setFont(new Font("Arial", Font.BOLD, 18));
        panel5.add(website3, gbcWebsite3);
        tabbedPane1.addTab("Launch Apps", panel5);
    }
}
