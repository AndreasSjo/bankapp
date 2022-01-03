package com.andreas;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class Index {

    public Index() {
        JPanel panel = new JPanel();
        JFrame frame = new JFrame("Start");
        frame.setSize(350, 350);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(panel);

        panel.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        JButton withdrawButton = new JButton("Withdraw");
            withdrawButton.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                
                }
            });
            c.fill = GridBagConstraints.HORIZONTAL;
            c.weightx = 0.5;
            c.insets = new Insets(20, 20, 0, 20);
            c.gridx = 0;
            c.gridy = 0;
        panel.add(withdrawButton, c);

        JButton depositButton = new JButton("Deposit");
            depositButton.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                
                }
            });
            c.fill = GridBagConstraints.HORIZONTAL;
            c.weightx = 0.5;
            c.insets = new Insets(20, 20, 0, 20);
            c.gridx = 1;
            c.gridy = 0;
        panel.add(depositButton, c);

        JButton statementButton = new JButton("Account statment");
            statementButton.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
            
                }
            });
            c.fill = GridBagConstraints.HORIZONTAL;
            c.weightx = 0.5;
            c.insets = new Insets(20, 20, 0, 20);
            c.gridx = 0;
            c.gridy = 1;
        panel.add(statementButton, c);

        JButton portfolioButton = new JButton("Account statment");
            portfolioButton.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
            
                }
            });
            c.fill = GridBagConstraints.HORIZONTAL;
            c.weightx = 0.5;
            c.insets = new Insets(20, 20, 0, 20);
            c.gridx = 1;
            c.gridy = 1;
        panel.add(portfolioButton, c);

        frame.setVisible(true);
    }
}
