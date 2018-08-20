package view;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionListener;

public class CommunicationPanel extends JPanel {

    private JTextField apiKeyField;
    private JTextField thresholdField;
    private JTextField classifierIdsField;

    private JButton setApiKeyBtn;
    private JButton selectImageBtn;

    public CommunicationPanel(String title) {
        Dimension size = new Dimension(555, 255);
        this.setPreferredSize(size);
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setBorder(BorderFactory.createTitledBorder(new LineBorder(Color.BLACK, 1), title,
                TitledBorder.LEFT, TitledBorder.TOP, null, Color.BLACK));


        this.add(APIKeyBoxPanel());
        this.add(thresholdPanel());
        this.add(classifierIdPanel());
    }

    /**
     * Top panel box, the API-key input.
     * @return the top Panel.
     */
    private JPanel APIKeyBoxPanel() {
        this.apiKeyField = new JTextField(25);
        this.setApiKeyBtn = new JButton("Set");

        JPanel panel = new JPanel();
        FlowLayout flowLayout = new FlowLayout();
        panel.setLayout(flowLayout);
        panel.add(new JLabel("API key: "), flowLayout);
        panel.add(apiKeyField, flowLayout);
        panel.add(setApiKeyBtn, flowLayout);
        return panel;
    }


    /**
     * Threshold panel.
     * @return the threshold score.
     */
    private JPanel thresholdPanel() {
        thresholdField = new JTextField("0.5", 5);
        JPanel panel = new JPanel();
        FlowLayout flowLayout = new FlowLayout();
        panel.setLayout(flowLayout);
        panel.add(new JLabel("Threshold: "), flowLayout);
        panel.add(thresholdField, flowLayout);
        return panel;
    }


    /**
     * Classifier IDs panel.
     * @return the classifier ids panel.
     */
    private JPanel classifierIdPanel() {
        this.classifierIdsField = new JTextField(25);
        this.selectImageBtn = new JButton("Select Image");

        JPanel panel = new JPanel();
        FlowLayout flowLayout = new FlowLayout();
        panel.setLayout(flowLayout);
        panel.add(new JLabel("Classifier ids: "), flowLayout);
        this.classifierIdsField.setText("\"lantman_1702805864\"");
        panel.add(classifierIdsField, flowLayout);
        panel.add(selectImageBtn, flowLayout);
        return panel;
    }


    /**
     * @return the API key
     */
    public String getAPIKey() {
        return this.apiKeyField.getText();
    }

    /**
     * Hides the API key
     */
    public void hideAPIKey() {
        this.apiKeyField.setText("*******************************");
    }

    public String getThreshold() {
        return this.thresholdField.getText();
    }

    public String getClassifierIds() {
        return this.classifierIdsField.getText();
    }

    /**
     * Adds a action listener for the set button.
     * @param actionListener
     */
    public void addSetAPIKeyListener(ActionListener actionListener) {
        this.setApiKeyBtn.addActionListener(actionListener);
    }


    /**
     * Adds a action listener for the select image button.
     * @param actionListener
     */
    public void addSelectImageListener(ActionListener actionListener) {
        this.selectImageBtn.addActionListener(actionListener);
    }

}
