package com.andreas;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import javax.swing.*;

public class Login {
    private String username, password;
    private boolean loginSuccess = false;
    JPanel panel = new JPanel();
    JFrame frame = new JFrame("Login");
    GridBagConstraints gbc = new GridBagConstraints();
    GridBagLayout gbl = new GridBagLayout();
    Insets inset = new Insets(10, 10, 10, 10);

    public Login() {
        frame.setSize(350, 350);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(panel);

        panel.setLayout(gbl);

        JLabel userLabel = new JLabel("User");


        JTextField userField = new JTextField("", 20);


        JLabel passwordLabel = new JLabel("Password");


        JPasswordField passwordField = new JPasswordField("", 20);


        JButton loginButton = new JButton("Login");
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                username = userField.getText();
                // TODO how to secure password?
                password = String.valueOf(passwordField.getPassword());

                if (tryLogin(username, password) == true) {
                    Index i = new Index(username);
                    frame.dispose();
                }

            }
        });


        JButton cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);

            }
        });


        addComponent(userLabel, 1, 0, 1, 1, inset);
        addComponent(userField, 1, 1, 1, 1, inset);
        addComponent(passwordLabel, 2, 0, 1, 1, inset);
        addComponent(passwordField, 2, 1, 1, 1, inset);
        addComponent(loginButton, 3, 0, 1, 1, inset);
        addComponent(cancelButton, 3, 1, 1, 1, inset);
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

    public boolean tryLogin(String username, String password) {
        boolean loginOk = false;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/bankdb", "user", "password");
            // bankdb is the database name, user is username and password is...
            // in my docker image. Change to whatever you have
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT id FROM user WHERE userName LIKE '" +
                    username + "'");

            if (rs.next()) {
                ResultSet rs2 = stmt.executeQuery(
                        "SELECT id FROM user WHERE userName LIKE '" +
                                username + "'" +
                                " AND password LIKE '" +
                                password.toString() + "'");
                if (rs2.next()) {
                    JOptionPane.showMessageDialog(null, "Login successful");
                    loginOk = true;
                    con.close();
                } else if (rs2.next() == false) {
                    JOptionPane.showMessageDialog(null, "Incorrect password");
                }
            } else if (rs.next() == false) {
                JOptionPane.showMessageDialog(null, "User does not exist");
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return loginOk;
    }
}
