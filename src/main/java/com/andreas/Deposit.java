package com.andreas;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Deposit {
    private  DBconnection dbc = new DBconnection();
    private JPanel panel = new JPanel();
    private JFrame frame = new JFrame("Withdraw");
    private GridBagConstraints gbc = new GridBagConstraints();
    private GridBagLayout gbl = new GridBagLayout();
    private Insets inset = new Insets(10, 10, 10, 10);
    private int accountId;
    private int accountBalance;
    private int depositAmount;

    Deposit(int accId){

        this.accountId = accId;
        getAccountBalance();

        frame.setSize(350, 350);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(panel);
        panel.setLayout(gbl);



        JLabel amountLabel = new JLabel("Amount");

        JTextField amountTextField = new JTextField("", 5);


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
    private void getAccountBalance(){
        try {

            ResultSet rs = dbc.statement.executeQuery(
                "SELECT balance FROM account WHERE user_id LIKE '" + this.accountId + "'"
            );
            // returns the first column from balance field
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
            if(String.valueOf(tempInteger).length() < 39 && tempInteger > 0){
                this.depositAmount = tempInteger;
                    executeTransaction();
                    frame.dispose();

            }
            else{
                JOptionPane.showMessageDialog(null, "Input only positve integer" +
                                                                        " and below 39 digits");
            }
        } catch (NumberFormatException ne) {
            JOptionPane.showMessageDialog(null, "input only digits please");
        }

    }

    private void executeTransaction(){
        // add the deposit amount to accountbalance
        this.accountBalance += depositAmount;

        // create an valid sql date format
        long millis=System.currentTimeMillis();
        java.sql.Date date=new java.sql.Date(millis);

        try {
            String updateBalanceSql = "UPDATE account SET balance = ? WHERE user_id = ?";

            PreparedStatement preparedBalanceStatement = dbc.connection.prepareStatement(updateBalanceSql);
            preparedBalanceStatement.setInt(1,this.accountBalance);
            preparedBalanceStatement.setInt(2, this.accountId);
            preparedBalanceStatement.executeUpdate();
            preparedBalanceStatement.close();

            String updateTransactionSql = "INSERT INTO transactions (id, user_id, type, amount, Date) " +
                "VALUES (0, ?, ?, ?, ?)";

            PreparedStatement preparedTransactionStatement = dbc.connection.prepareStatement(updateTransactionSql);
            preparedTransactionStatement.setInt(1,this.accountId);
            preparedTransactionStatement.setString(2, "DEPOSIT");
            preparedTransactionStatement.setInt(3, this.depositAmount);
            preparedTransactionStatement.setDate(4,date);
            preparedTransactionStatement.executeUpdate();
            preparedBalanceStatement.close();


        } catch (Exception e) {
            System.out.println(e);

        }
    }

}
