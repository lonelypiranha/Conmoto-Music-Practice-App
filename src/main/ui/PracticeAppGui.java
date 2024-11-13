package ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class PracticeAppGui implements ActionListener {
    JFrame frame;
    JLabel labelOpening;
    ImageIcon logo;
    JButton buttonOpening;
    JPanel panelOpening;
    JPanel panelButton;

    public PracticeAppGui() {
        frame = new JFrame();
        panelOpening = new JPanel();
        panelButton = new JPanel();
        logo = new ImageIcon("src/main/ui/on-2.png");

        labelOpening = new JLabel();
        labelOpening.setIcon(logo);
        labelOpening.setText("Welcome To Conmoto Music Practice App!");
        labelOpening.setHorizontalTextPosition(JLabel.CENTER);
        labelOpening.setVerticalTextPosition(JLabel.BOTTOM);
        labelOpening.setForeground(Color.WHITE);
        labelOpening.setFont(new Font("Futura", Font.BOLD, 30));
        labelOpening.setIconTextGap(-50);
        labelOpening.setHorizontalAlignment(JLabel.CENTER);
        labelOpening.setVerticalAlignment(JLabel.TOP);

        buttonOpening = new JButton();
        buttonOpening.setText("Start App");
        buttonOpening.setSize(100, 100);
        buttonOpening.setHorizontalAlignment(JButton.CENTER);
        buttonOpening.setVerticalAlignment(JButton.CENTER);
        buttonOpening.addActionListener(this);
        buttonOpening.setFocusable(false);
        buttonOpening.setHorizontalTextPosition(JButton.CENTER);
        buttonOpening.setVerticalTextPosition(JButton.CENTER);
        buttonOpening.setFont(new Font("Futura", Font.PLAIN, 20));
        buttonOpening.setForeground(new Color(2, 5, 98));

        panelOpening.setBackground(new Color(0, 0, 0, 0));
        panelButton.setBackground(new Color(0, 0, 0, 0));
        panelButton.setLayout(new GridBagLayout());
        panelButton.add(buttonOpening);

        panelOpening.setLayout(new BoxLayout(panelOpening, BoxLayout.PAGE_AXIS));
        panelOpening.add(labelOpening);
        panelOpening.add(Box.createVerticalStrut(40));
        panelOpening.add(panelButton);
        

        frame.setSize(840, 840);
        frame.setTitle("Conmoto Music Practice App");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new FlowLayout());
        frame.add(panelOpening);
        frame.setVisible(true);
        frame.getContentPane().setBackground(new Color(2, 5, 98));
        

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'actionPerformed'");
    }

    
}
