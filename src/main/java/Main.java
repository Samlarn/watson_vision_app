import controller.WatsonVisionAppController;
import model.WatsonVisionCommunicator;
import view.MainWindow;

import javax.swing.*;

/**
 * Main class for Watson vision application.
 */

public class Main {

    public static void main(String[] args) {
        MainWindow window = new MainWindow("Watson Application");
        SwingUtilities.invokeLater(window);

        WatsonVisionCommunicator communicator = new WatsonVisionCommunicator();

        new WatsonVisionAppController(window, communicator);

    }

}
