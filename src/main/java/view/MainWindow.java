package view;

import javax.swing.*;
import java.awt.*;

public class MainWindow extends JFrame {

    private CommunicationPanel commPanel;


    public MainWindow(String title) {
        super(title);
        //this.setSize(755, 655);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.setLayout(new BorderLayout());

        commPanel = new CommunicationPanel("Watson Communication");

        Container container = this.getContentPane();

        container.setLayout(new BorderLayout());
        container.add(commPanel, BorderLayout.WEST);
        //container.add(outputPanel, BorderLayout.EAST);
        this.pack();
    }


    public CommunicationPanel getCommunicationPanel() {
        return this.commPanel;
    }


    public void setVisible() {
        this.setVisible(true);
    }


}

