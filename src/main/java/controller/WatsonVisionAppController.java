package controller;

import model.WatsonVisionCommunicator;
import view.CommunicationPanel;
import view.MainWindow;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WatsonVisionAppController {

    private MainWindow window;
    private WatsonVisionCommunicator watsonCommunicator;

    public WatsonVisionAppController(MainWindow window, WatsonVisionCommunicator watsonCommunicator) {
        this.window = window;
        this.watsonCommunicator = watsonCommunicator;

        addWatsonCommunicationListener();
    }

    /**
     * Adds a listener for the set API button.
     */
    private void addWatsonCommunicationListener() {
        window.getCommunicationPanel().addSetAPIKeyListener(new SetAPIKeyListener());
    }




    // ----------------------------------- listeners ----------------------------------------------------------------
    /**
     * Listens to messages from user when send button is pressed.
     */
    class SetAPIKeyListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            CommunicationPanel commPanel = window.getCommunicationPanel();
            String APIkey = commPanel.getAPIKey();

            watsonCommunicator.setAPIKey(APIkey);
            commPanel.hideAPIKey();

            System.out.println("API key: "+APIkey);
        }

    }
}
