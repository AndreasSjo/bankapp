package com.andreas;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import javax.swing.*;

public class Login {
    
    public Login(){
        JPanel panel = new JPanel();
        JFrame frame = new JFrame("Login");
        frame.setSize(350, 350);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(panel);

        panel.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        JLabel label = new JLabel("User");
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.5;
        c.insets = new Insets(0,20,0,0);
        c.gridx = 0;
        c.gridy = 0;
        panel.add(label, c);

        JTextField userText = new JTextField("", 20);
        c.fill = c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.5;
        c.insets = new Insets(0,0,0,20);
        c.gridx = 1;
        c.gridy = 0;
        panel.add(userText, c);

        JLabel passwordLabel = new JLabel("Password");
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.5;
        c.insets = new Insets(20,20,0,20);
        c.gridx = 0;
        c.gridy = 1;
        panel.add(passwordLabel, c);

        
        JPasswordField passwordField = new JPasswordField("", 20);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.5;
        c.insets = new Insets(20,20,0,20);
        c.gridx = 1;
        c.gridy = 1;
        panel.add(passwordField, c);

        JButton loginButton = new JButton("Login");
            loginButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                tryLogin();
                
                }
            });
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.5;
        c.insets = new Insets(20,20,0,20);
        c.gridx = 0;
        c.gridy = 2;
        panel.add(loginButton, c);

        
        JButton cancelButton = new JButton("Cancel");
            cancelButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                  System.exit(0);
                
                }
            });
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.5;
        c.insets = new Insets(20,20,0,20);
        c.gridx = 1;
        c.gridy = 2;
        panel.add(cancelButton, c);

        frame.setVisible(true);

    }
    public void tryLogin(){
        try{  
            Class.forName("com.mysql.cj.jdbc.Driver");  
            Connection con=DriverManager.getConnection(  
            "jdbc:mysql://localhost:3306/bankdb","user","password");  
            //here sonoo is database name, root is username and password  
            Statement stmt=con.createStatement();  
            ResultSet rs=stmt.executeQuery("select userName from user where id = 1");  
            while(rs.next())  
            System.out.println(rs.getString(1));  
            con.close();  
            }catch(Exception e){
                 System.out.println(e);
                }  
            }  
    }


