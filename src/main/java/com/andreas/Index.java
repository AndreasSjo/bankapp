package com.andreas;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class Index {
    // Establish a connection to database
    private DBconnection databaseConnection = new DBconnection();
    private JPanel panel = new JPanel();
    private JFrame frame = new JFrame("Start");
    private GridBagConstraints gbc = new GridBagConstraints();
    private GridBagLayout gbl = new GridBagLayout();
    private Insets inset = new Insets(10, 10, 10, 10);

    Index(String user) {
        // Create an object of PersonData to retrieve info
        // passing a databaseconnection and the username
        PersonData personData = new PersonData(databaseConnection,user);
        frame.setSize(350, 350);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(panel);

        panel.setLayout(gbl);

        JLabel welcomeHeader = new JLabel("Welcome " + personData.firstName + " " + personData.lastName);

        JButton withdrawButton = new JButton("Withdraw");
        withdrawButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                // Creates a new object of withdrawal (basically a window)
                // passing on the accountId which is same as user_id
                Withdrawal w = new Withdrawal(personData.accountId);
            }
        });


        JButton depositButton = new JButton("Deposit");
        depositButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                Deposit d = new Deposit(personData.accountId);
            }
        });

        JButton statementButton = new JButton("Acc statment");
        statementButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                Transactions t = new Transactions(personData.accountId);
            }
        });

        JButton portfolioButton = new JButton("Portfolio");
        portfolioButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null,"Under construction");
            }
        });

        addComponent(welcomeHeader, 0, 0, 2, 1, inset);
        addComponent(withdrawButton, 1, 0, 1, 1, inset);
        addComponent(depositButton, 1, 1, 1, 1, inset);
        addComponent(statementButton, 2, 0, 1, 1, inset);
        addComponent(portfolioButton, 2, 1, 1, 1, inset);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private void addComponent(Component component, int y,
            int x, int width, int height, Insets inset) {

        gbc.gridy = y;
        gbc.gridx = x;
        gbc.gridwidth = width;
        gbc.gridheight = height;
        gbc.insets = inset;
        gbl.setConstraints(component, gbc);
        panel.add(component);
    }
}






