package com.andreas;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

import javax.swing.*;

public class Withdrawal {
    DBconnection dbc = new DBconnection();
    JPanel panel = new JPanel();
    JFrame frame = new JFrame("Withdraw");
    GridBagConstraints gbc = new GridBagConstraints();
    GridBagLayout gbl = new GridBagLayout();
    Insets inset = new Insets(10, 10, 10, 10);
    int accountId;
    int accountBalance;
    int withdrawalAmount;

    public Withdrawal(int accId) {
        this.accountId = accId;
        getAccountBalance();
        System.out.println(accountBalance);

        frame.setSize(350, 350);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(panel);
        panel.setLayout(gbl);



        JLabel amountLabel = new JLabel("Amount");

        JTextField amountTextField = new JTextField("", 10);

        
        JButton confirmButton = new JButton("Confirm");
            confirmButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {

                    controlInput(amountTextField.getText());
                }
            });
            addComponent(amountLabel, 1, 0, 1, 1, inset);
            addComponent(amountTextField, 1, 1, 1, 1, inset);
            addComponent(confirmButton, 2, 0, 2, 1, inset);
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

    private void getAccountBalance(){
        try {

            ResultSet rs = dbc.stmt.executeQuery(
                "SELECT balance FROM account WHERE user_id LIKE '" + this.accountId + "'"
                );
            // returns the first row from balance field
            while (rs.next()) {
                this.accountBalance = rs.getInt(1);
            }

        } catch (Exception e) {
            System.out.println(e);
        }
    }
    private void controlInput(String input){
        int tempInteger;
        // First try to parse the input-string into an integer
        try {
            tempInteger = Integer.parseInt(input);
        // Then check the number of digits as the table only can handle 38 digits
            if(String.valueOf(tempInteger).length() <= 38){
                this.withdrawalAmount = tempInteger;
                System.out.println("acceptable input");
            }
            else{
                JOptionPane.showMessageDialog(null, "Whoa! thats to much");
            }
        } catch (NumberFormatException ne) {
            JOptionPane.showMessageDialog(null, "input only digits please");
        }

    }

     private void executeTransaction(){
        try {

            ResultSet rs = dbc.stmt.executeQuery(
                "SELECT * FROM user "
                );
            // In the table 'user' 1st row is 'id',
            // 2nd is 'firstName', 3rd is 'lastName'
            while (rs.next()) {

            }

        } catch (Exception e) {
            System.out.println(e);
        }
    
    } 
}
