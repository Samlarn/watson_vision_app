import controller.WatsonVisionAppController;
import model.WatsonVisionCommunicator;
import view.MainWindow;

/**
 * Main class for Watson vision application.
 */

public class Main {

    public static void main(String[] args) {
        MainWindow window = new MainWindow("Watson application");
        window.setVisible();

        WatsonVisionCommunicator communicator = new WatsonVisionCommunicator();

        new WatsonVisionAppController(window, communicator);


    }

}
