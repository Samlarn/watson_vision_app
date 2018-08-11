package view;

import javax.swing.*;
import java.awt.*;

public class MainWindow extends JFrame implements Runnable {

    private CommunicationPanel commPanel;
    private OutputPanel outputPanel;


    public MainWindow(String title) {
        super(title);
        //this.setSize(755, 655);
    }


    public CommunicationPanel getCommunicationPanel() {
        return this.commPanel;
    }

    public OutputPanel getOutputPanel() {
        return this.outputPanel;
    }


    public void run() {
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.setLayout(new BorderLayout());

        this.commPanel = new CommunicationPanel("Watson Communication");
        this.outputPanel = new OutputPanel("Outputs");

        Container container = this.getContentPane();

        container.setLayout(new BorderLayout());
        container.add(commPanel, BorderLayout.WEST);
        container.add(outputPanel, BorderLayout.CENTER);
        this.pack();
        this.setVisible(true);
    }
}

