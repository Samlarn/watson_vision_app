package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class CommunicationPanel extends JPanel {

    private JTextField apiKeyField;
    private JButton setApiKeyBtn;

    public CommunicationPanel(String title) {
        apiKeyField = new JTextField(27);
        setApiKeyBtn = new JButton("Set");

        FlowLayout flowLayout = new FlowLayout();
        this.setLayout(flowLayout);
        this.add(new JLabel("API key: "), flowLayout);
        this.add(apiKeyField, flowLayout);
        this.add(setApiKeyBtn, flowLayout);

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

    /**
     * Adds a action listener for the set button.
     * @param actionListener
     */
    public void addSetAPIKeyListener(ActionListener actionListener) {
        setApiKeyBtn.addActionListener(actionListener);
    }



}
