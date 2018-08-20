package controller;

import model.ClassifiedImageResult;
import model.WatsonVisionCommunicator;
import view.CommunicationPanel;
import view.MainWindow;
import view.OutputPanel;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;

public class WatsonVisionAppController {

    private MainWindow window;
    private WatsonVisionCommunicator watsonCommunicator;

    private boolean imageSelected = false;
    private String imagePath;

    public WatsonVisionAppController(MainWindow window, WatsonVisionCommunicator watsonCommunicator) {
        this.window = window;
        this.watsonCommunicator = watsonCommunicator;

        addSetAPIKeyListener();
        addSelectImageListener();
        addClassifyImageListener();
    }


    public void setImageSelected(boolean imageSelected) {
        this.imageSelected = imageSelected;
    }

    public boolean imageIsSelected() {
        return this.imageSelected;
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

    private void addClassifyImageListener() {
        window.getOutputPanel().addClassifyImageListener(new ClassifyImageListener());
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
                imagePath = image.getAbsolutePath();
                try {
                    BufferedImage buffImage = ImageIO.read(image);
                    outputPanel.displayImage(resize(buffImage, 255, 235));
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
                System.out.println("Image: "+imagePath);
                setImageSelected(true);
            }
            else {
                System.out.println("Select a image file!");
            }

        }

        /**
         * Resize an image.
         * @param img the image
         * @param newW new with of image
         * @param newH new height of image
         * @return the new resized image.
         */
        private BufferedImage resize(BufferedImage img, int newW, int newH) {
            Image tmp = img.getScaledInstance(newW, newH, Image.SCALE_SMOOTH);
            BufferedImage dimg = new BufferedImage(newW, newH, BufferedImage.TYPE_INT_ARGB);

            Graphics2D g2d = dimg.createGraphics();
            g2d.drawImage(tmp, 0, 0, null);
            g2d.dispose();

            return dimg;
        }
    }

    /**
     * Listens to classify image button.
     */
    class ClassifyImageListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            OutputPanel outputPanel = window.getOutputPanel();
            if(imageIsSelected()) {
                System.out.println("classify");
                String classifierIds = window.getCommunicationPanel().getClassifierIds();
                String threshold = window.getCommunicationPanel().getThreshold();
                try {
                    String imageName = getImageName(imagePath);
                    outputPanel.displayText("#Image: " + imageName);
                    List<ClassifiedImageResult> classifiedImages =  watsonCommunicator.getClassifiedImages(imageName, imagePath, classifierIds, threshold);
                    for(ClassifiedImageResult classifiedImage : classifiedImages) {
                        Set<String> classNames = classifiedImage.getImageResult().keySet();
                        for(String className : classNames) {
                            String score = classifiedImage.getClassScore(className);
                            outputPanel.displayText(className + ": " + score);
                            System.out.println(className + ": " + score);
                        }
                    }

                } catch (FileNotFoundException e1) {
                    e1.printStackTrace();
                }
            }
            else {
                outputPanel.displayText("select an image");
            }
        }

        private String getImageName(String imagePath) {
            String[] tmpArr = imagePath.split(Pattern.quote(File.separator));
            String imageName = tmpArr[tmpArr.length-1];
            return imageName;
        }

    }

}
