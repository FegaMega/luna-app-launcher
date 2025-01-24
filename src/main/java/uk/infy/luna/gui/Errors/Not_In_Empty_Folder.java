package uk.infy.luna.gui.Errors;

import javax.swing.*;
import java.util.List;

public class Not_In_Empty_Folder {
    public static void showError(List<String> unallowedFiles) {
        String message;
            message = "Please put the app in a empty folder for it to work.";
        SwingUtilities.invokeLater(() -> {
            JOptionPane.showMessageDialog(
                    null,
                    message,
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
            System.exit(50);
        });
    }
}
