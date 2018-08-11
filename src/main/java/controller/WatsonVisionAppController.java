package controller;

import model.WatsonVisionCommunicator;
import view.CommunicationPanel;
import view.MainWindow;
import view.OutputPanel;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class WatsonVisionAppController {

    private MainWindow window;
    private WatsonVisionCommunicator watsonCommunicator;

    public WatsonVisionAppController(MainWindow window, WatsonVisionCommunicator watsonCommunicator) {
        this.window = window;
        this.watsonCommunicator = watsonCommunicator;

        addSetAPIKeyListener();
        addSelectImageListener();
    }

    /**
     * Adds a listener for the set API button.
     */
    private void addSetAPIKeyListener() {
        window.getCommunicationPanel().addSetAPIKeyListener(new SetAPIKeyListener());
    }


    private void addSelectImageListener() {
        window.getCommunicationPanel().addSelectImageListener(new SelectImageListener());
    }




    // ----------------------------------- listeners ----------------------------------------------------------------
    /**
     * Listens to set API key button.
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

    /**
     * Listens to select image button.
     */
    class SelectImageListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            //CommunicationPanel commPanel = window.getCommunicationPanel();
            OutputPanel outputPanel = window.getOutputPanel();
            JFileChooser fc = new JFileChooser();
            JPanel panel = new JPanel();

            int returnVal = fc.showOpenDialog(panel);

            if(returnVal == JFileChooser.FILES_ONLY) {
                File image = fc.getSelectedFile();
                String path = image.getAbsolutePath();
                outputPanel.displayText(path);
                try {
                    BufferedImage buffImage = ImageIO.read(image);
                    outputPanel.displayImage(path, buffImage);
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
                System.out.println("Image: "+path);
            }
            else {
                System.out.println("Select a image file!");
            }

            System.out.println("Select Image! "+ returnVal);
        }

    }
}
