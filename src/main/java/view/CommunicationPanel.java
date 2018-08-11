package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class CommunicationPanel extends JPanel {

    private JTextField apiKeyField;
    private JTextField classifierIdsField;

    private JButton setApiKeyBtn;

    public CommunicationPanel(String title) {
        Dimension size = new Dimension(455, 355);
        this.setPreferredSize(size);

        this.apiKeyField = new JTextField(25);
        this.classifierIdsField = new JTextField(25);

        this.setApiKeyBtn = new JButton("Set");

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.add(APIKeyBoxPanel());
        this.add(classifierIdPanel());
    }

    /**
     * Top panel box, the API-key input.
     * @return the top Panel.
     */
    private JPanel APIKeyBoxPanel() {
        JPanel panel = new JPanel();
        FlowLayout flowLayout = new FlowLayout();
        panel.setLayout(flowLayout);
        panel.add(new JLabel("API key: "), flowLayout);
        panel.add(apiKeyField, flowLayout);
        panel.add(setApiKeyBtn, flowLayout);
        return panel;
    }

    /**
     * Classifier IDs panel.
     * @return the classifier ids panel.
     */
    private JPanel classifierIdPanel() {
        JPanel panel = new JPanel();
        FlowLayout flowLayout = new FlowLayout();
        panel.setLayout(flowLayout);
        panel.add(new JLabel("Classifier ids: "), flowLayout);
        this.classifierIdsField.setText("\"lantmannen_994293677\",\"bread_1474507279\"");
        panel.add(classifierIdsField, flowLayout);
        return panel;
    }

    /**
     * @return the API key
     */
    public String getAPIKey() {
        return apiKeyField.getText();
    }

    /**
     * Hides the API key
     */
    public void hideAPIKey() {
        apiKeyField.setText("*******************************");
    }

    public String getClassifierIds() {
        return this.classifierIdsField.getText();
    }

    /**
     * Adds a action listener for the set button.
     * @param actionListener
     */
    public void addSetAPIKeyListener(ActionListener actionListener) {
        setApiKeyBtn.addActionListener(actionListener);
    }



}
