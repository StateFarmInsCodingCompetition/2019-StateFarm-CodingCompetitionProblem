package codingcompetition2019.gui;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WelcomeScreen extends JFrame {

    private JComboBox cbData;

    public WelcomeScreen() {
        super("Natural Disaster Info");
        createGUIComponents();
        setSize(400, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    private void createGUIComponents() {
        JPanel dataTypePanel = new JPanel();
        dataTypePanel.setLayout(new GridLayout(1, 2));
        dataTypePanel.setBorder(new EmptyBorder(20, 20, 20, 20));
        dataTypePanel.add(new JLabel("Select Data Type"));

        String[] data = {"Natural Disasters by Type"};
        cbData = new JComboBox(data);
        dataTypePanel.add(cbData);

        JButton btnGetData = new JButton("View Data");
        btnGetData.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                switch(cbData.getSelectedIndex()){
                    case 0:
                        (new AllNaturalDisasters()).setVisible(true); break;
//                    case 1:
//                        (new Earthquake()).setVisible(true); break;
//                    case 3:
//                        (new Volcano()).setVisible(true); break;
                }
            }
        });

        Container pane = this.getContentPane();
        pane.setLayout(new BoxLayout(pane, BoxLayout.Y_AXIS));

        pane.add(Box.createVerticalStrut(25));
        pane.add(new JLabel("Natural Disaster Info"));
        pane.add(Box.createVerticalStrut(50));
        pane.add(dataTypePanel);
        pane.add(Box.createVerticalStrut(50));
        pane.add(btnGetData);
        pane.add(Box.createVerticalStrut(25));
    }


}
