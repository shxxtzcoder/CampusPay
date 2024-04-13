import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.sql.*;

public class VendorHome {
    public void show() {
        JFrame f = new JFrame("Home");
        f.setBounds(300, 90, 900, 600);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setLayout(null);

        Container container = f.getContentPane();
        container.setBackground(new Color(243, 238, 234));

        JLabel title = new JLabel("Home Page");
        title.setFont(new Font("MONOSPACED", Font.BOLD, 30));
        title.setBounds(350, 30, 300, 40);
        container.add(title);

        JLabel total = new JLabel("Total Amount Earned: ");
        total.setFont(new Font("Arial", Font.PLAIN, 20));
        total.setBounds(350, 100, 500, 20);
        container.add(total);

        try {
            Conn c = new Conn();
            CallableStatement cs = c.con.prepareCall("{call get_total_amount_earned_by_vendor(?, ?)}");
            cs.setString(1, User.getInstance().getId());
            cs.registerOutParameter(2, Types.INTEGER);
            cs.execute();
            int total_amount_earned = cs.getInt(2);
            JLabel totalAmount = new JLabel(Integer.toString(total_amount_earned));
            totalAmount.setFont(new Font("Arial", Font.PLAIN, 20));
            totalAmount.setBounds(400, 100, 200, 20);
            container.add(totalAmount);
        } catch (Exception e) {
            System.out.println(e);
        }

        int buttonWidth = 200;
        int buttonHeight = 30;
        int buttonX = 350;

        JButton transactionHistory = new JButton("Transaction History");
        configureButton(transactionHistory, buttonX, 140, buttonWidth, buttonHeight);
        transactionHistory.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                VendorTransactionHistory transaction = new VendorTransactionHistory();
                transaction.show();
                f.dispose();
            }
        });
        container.add(transactionHistory);

        JButton itemsSold = new JButton("My Items");
        configureButton(itemsSold, buttonX, 190, buttonWidth, buttonHeight);
        itemsSold.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                VendorItems items = new VendorItems();
                items.show();
                f.dispose();
            }
        });
        container.add(itemsSold);

        JButton newTransaction = new JButton("New Transaction");
        configureButton(newTransaction, buttonX, 240, buttonWidth, buttonHeight);
        newTransaction.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                VendorTransactionProcess transaction = new VendorTransactionProcess();
                transaction.show();
                f.dispose();
            }
        });
        container.add(newTransaction);

        JButton editVendor = new JButton("Edit Details");
        configureButton(editVendor, buttonX, 290, buttonWidth, buttonHeight);
        editVendor.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                EditVendor edit = new EditVendor();
                edit.show();
            }
        });
        container.add(editVendor);

        JButton orders = new JButton("Show Orders");
        configureButton(orders, buttonX, 340, buttonWidth, buttonHeight);
        orders.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ShowOrders showOrders = new ShowOrders();
                showOrders.show();
                f.dispose();
            }
        });
        container.add(orders);

        JButton logout = new JButton("Logout");
        configureButton(logout, buttonX + 40, 500, 100, buttonHeight);
        logout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                User.getInstance().clear();
                LoginSystem login = new LoginSystem();
                login.show();
                f.dispose();
            }
        });
        container.add(logout);

        f.setVisible(true);
    }

    private void configureButton(JButton button, int x, int y, int width, int height) {
        button.setFont(new Font("Arial", Font.PLAIN, 15));
        button.setBounds(x, y, width, height);
        button.setBackground(new Color(176, 166, 149)); // B0A695
        button.setBorder(BorderFactory.createLineBorder(new Color(176, 166, 149), 2)); // B0A695
    }
}
