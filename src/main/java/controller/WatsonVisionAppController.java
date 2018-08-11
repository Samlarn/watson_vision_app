package controller;

import model.WatsonVisionCommunicator;
import view.MainWindow;

import javax.swing.*;
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
            JPanel commPanel = window.getCommunicationPanel();

            System.out.println("API key set!");
        }

    }
}
