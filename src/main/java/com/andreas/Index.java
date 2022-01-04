package com.andreas;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class Index {
    JPanel panel = new JPanel();
    JFrame frame = new JFrame("Start");
    GridBagConstraints gbc = new GridBagConstraints();
    GridBagLayout gbl = new GridBagLayout();
    Insets inset = new Insets(10, 10, 10, 10);

    public Index(String user) {
        PersonData pD = new PersonData(user);
        frame.setSize(350, 350);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(panel);

        panel.setLayout(gbl);

        JLabel welcomeHeader = new JLabel("Welcome " + pD.fName + " " + pD.lName);

        JButton withdrawButton = new JButton("Withdraw");
        withdrawButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
            }
        });


        JButton depositButton = new JButton("Deposit");
        depositButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
            }
        });

        JButton statementButton = new JButton("Acc statment");
        statementButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
            }
        });

        JButton portfolioButton = new JButton("Portfolio");
        portfolioButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
            }
        });

        addComponent(welcomeHeader, 0, 0, 2, 1, inset);
        addComponent(withdrawButton, 1, 0, 1, 1, inset);
        addComponent(depositButton, 1, 1, 1, 1, inset);
        addComponent(statementButton, 2, 0, 1, 1, inset);
        addComponent(portfolioButton, 2, 1, 1, 1, inset);

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

class PersonData {
    String fName, lName;
    int accId;

    public PersonData(String username) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bankdb", "user", "password");
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM user WHERE userName LIKE '" + username + "'");

            while (rs.next()) {
                this.accId = rs.getInt(1);
                this.fName = rs.getString(2);
                this.lName = rs.getString(3);
                con.close();
            }

        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
