package com.andreas;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class Transactions {
    private  DBconnection dbc = new DBconnection();
    private JPanel panel = new JPanel();
    private JFrame frame = new JFrame("Login");
    private GridBagConstraints gbc = new GridBagConstraints();
    private GridBagLayout gbl = new GridBagLayout();
    private Insets inset = new Insets(10, 10, 10, 10);
    private String transactionsText;
    private int accountId;
    String[] columnNames = {"Type", "Amount", "Date"};
    DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);

    public Transactions(int accId){
        this.accountId = accId;
        frame.setSize(350, 350);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(panel);
        panel.setLayout(gbl);

        JTable jt = getTransactions();
            jt.setPreferredScrollableViewportSize(jt.getPreferredSize());
            jt.setFillsViewportHeight(true);

        JScrollPane scroll = new JScrollPane (jt,
            JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        JButton cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
            }
        });

        addComponent(scroll, 0, 0, 3, 1, inset);
        addComponent(cancelButton, 1, 2, 1, 1, inset);

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

    }
    private void addComponent(Component component, int y, int x, int width, int height, Insets inset) {
        gbc.gridy = y;
        gbc.gridx = x;
        gbc.gridwidth = width;
        gbc.gridheight = height;
        gbc.insets = inset;
        gbl.setConstraints(component, gbc);
        panel.add(component);
    }
    private JTable getTransactions(){
        try {
            ResultSet rs = dbc.statement.executeQuery(
                "SELECT type,amount,date FROM transactions WHERE user_id LIKE '" + this.accountId + "'");


            while (rs.next()) {
                String type = rs.getString("Type");
                String amount = rs.getString("Amount");
                String date = rs.getString("Date");

                // create a single array of one row's worth of data
                String[] data = { type, amount, date } ;

                // and add this row of data into the table model
                tableModel.addRow(data);
            }



        }catch (Exception e){
            System.out.println(e);
        }
        JTable jt = new JTable(tableModel);
        return jt;
    }
}
