package view;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class OutputPanel extends JPanel {

    private JTextArea outputTextArea;

    private JLabel imageLabel;

    public OutputPanel(String title) {
        Dimension size = new Dimension(355, 755);
        this.setPreferredSize(size);
        this.setLayout(new BorderLayout());
        this.setBorder(BorderFactory.createTitledBorder(new LineBorder(Color.BLACK, 1), title,
                TitledBorder.LEFT, TitledBorder.TOP, null, Color.BLACK));

        //this.imageLabel = new JLabel();

        this.outputTextArea = new JTextArea();
        this.outputTextArea = new JTextArea();
        this.outputTextArea.setEditable(false);

        this.imageLabel = new JLabel();
        this.add(imageLabel, BorderLayout.NORTH);

        this.add(new JScrollPane(outputTextArea), BorderLayout.CENTER);
    }

    /**
     * Displays text in the output text area.
     * @param text text to be displayed.
     */
    public void displayText(String text) {
        this.outputTextArea.append(text+"\n");
    }

    /**
     * displays image on the GUI
     * @param path path to image
     * @param image the buffer image.
     * @throws IOException throws if no image file found.
     */
    public void displayImage(String path, BufferedImage image) throws IOException {
        imageLabel.setIcon(new ImageIcon(image));
        this.add(imageLabel, BorderLayout.NORTH);
        this.repaint();
    }

    
}
