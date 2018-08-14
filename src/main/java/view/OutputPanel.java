package view;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class OutputPanel extends JPanel {

    private JTextArea outputTextArea;
    private JLabel imageLabel;

    private JButton classifyBtn;

    public OutputPanel(String title) {
        Dimension size = new Dimension(255, 455);
        this.setPreferredSize(size);
        this.setLayout(new BorderLayout());
        this.setBorder(BorderFactory.createTitledBorder(new LineBorder(Color.BLACK, 1), title,
                TitledBorder.LEFT, TitledBorder.TOP, null, Color.BLACK));

        this.outputTextArea = new JTextArea();
        this.outputTextArea.setEditable(false);

        this.imageLabel = new JLabel();
        this.classifyBtn = new JButton("Classify");

        JPanel northPanel = new JPanel(new BorderLayout());
        northPanel.add(imageLabel, BorderLayout.CENTER);
        northPanel.add(classifyBtn, BorderLayout.SOUTH);

        this.add(northPanel, BorderLayout.NORTH);
        this.add(new JScrollPane(outputTextArea), BorderLayout.CENTER);
    }

    /**
     * Displays text in the output text area.
     * @param text text to be displayed.
     */
    public void displayText(String text) {
        this.outputTextArea.append(" " + text + "\n");
    }

    /**
     * displays image on the GUI
     * @param image the buffer image.
     * @throws IOException throws if no image file found.
     */
    public void displayImage(BufferedImage image) {
        imageLabel.setIcon(new ImageIcon(image));
        this.add(imageLabel, BorderLayout.NORTH);
        this.repaint();
    }


    /**
     * Adds a action listener for the set button.
     * @param actionListener
     */
    public void addClassifyImageListener(ActionListener actionListener) {
        this.classifyBtn.addActionListener(actionListener);
    }
}
