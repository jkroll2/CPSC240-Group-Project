import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

class ButtonListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        //See account balance after inputting account number
        if (e.getActionCommand().equals("See Account Balance")) {
            String text = JOptionPane.showInputDialog(null, "Enter your account number to view the balance: ", "See Account Balance", JOptionPane.INFORMATION_MESSAGE);
            try {
                JOptionPane.showMessageDialog(null, "Balance: " + Bank.getAccount(Integer.parseInt(text)).getMoney(), "View Balance", JOptionPane.INFORMATION_MESSAGE);
            } catch (NullPointerException n) {
                JOptionPane.showMessageDialog(null, "Not a valid account number.", "Error", JOptionPane.INFORMATION_MESSAGE);
            }
        }

        //Withdraw from an account
        else if (e.getActionCommand().equals("Withdraw From Account")) {
            String text = JOptionPane.showInputDialog(null,"Enter your account number to withdraw: ", "Withdraw", JOptionPane.INFORMATION_MESSAGE);
            String text2 = JOptionPane.showInputDialog(null, "Enter the amount to withdraw: ", "Withdraw", JOptionPane.INFORMATION_MESSAGE);
            try {
                Bank.getAccount(Integer.parseInt(text)).withdraw(Integer.parseInt(text2));
            } catch (NullPointerException n) {
                JOptionPane.showMessageDialog(null, "Not a valid account number.", "Error", JOptionPane.INFORMATION_MESSAGE);
            }
            try {
                Bank.save(Bank.getAccount(Integer.parseInt(text)));
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, "Could not access bank account.", "Error", JOptionPane.INFORMATION_MESSAGE);
            }
            JOptionPane.showMessageDialog(null, "Amount Withdrawn", "Success", JOptionPane.INFORMATION_MESSAGE);
        }

        //Deposit into account
        else if (e.getActionCommand().equals("Deposit Into Account")) {
            String text = JOptionPane.showInputDialog(null, "Enter your account number to deposit into: ", "Deposit", JOptionPane.INFORMATION_MESSAGE);
            String text2 = JOptionPane.showInputDialog(null, "Enter the amount to deposit: ", "Deposit", JOptionPane.INFORMATION_MESSAGE);
            try {
                Bank.getAccount(Integer.parseInt(text)).deposit(Integer.parseInt(text2));
            } catch (NullPointerException n) {
                JOptionPane.showMessageDialog(null, "Not a valid account number.", "Error", JOptionPane.INFORMATION_MESSAGE);
            }
            try {
                Bank.save(Bank.getAccount(Integer.parseInt(text)));
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, "Could not access bank account.", "Error", JOptionPane.INFORMATION_MESSAGE);
            }
            JOptionPane.showMessageDialog(null, "Amount Deposited", "Success", JOptionPane.INFORMATION_MESSAGE);
        }

        //Transfer money between accounts
        else if (e.getActionCommand().equals("Transfer Money Between Accounts")) {
            JOptionPane.showMessageDialog(null, "Transfer", "Transfer", JOptionPane.INFORMATION_MESSAGE);
        }

    }
}

public class Main {
    public static void main(String[] args) {

        //TEST BANK ACCOUNT
        //I have been using this to test the 'See Account Balance' button and
        //the 'Withdraw From Account' button
        //Note that the account number is automatically set to 1000000 in the BankAccount.java BankAccount() constructor
        BankAccount acc1 = new BankAccount(AccountType.CHECKING, 158);
        try {
            Bank.save(acc1);
        } catch (IOException e) {
            System.out.println("IOException");
        }

        // create and set up the window.
        JFrame frame = new JFrame("Bank Management");

        // make the program close when the window closes
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //makes layout
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setLayout(new GridLayout(4, 1));
        panel.setMaximumSize(new Dimension(400, 400));
        frame.getContentPane().setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));

        //makes label for top of window
        JLabel label = new JLabel("Welcome to Bank Management");
        frame.getContentPane().add(label);

        //makes each button for each option
        JButton balanceButton = new JButton("See Account Balance");
        balanceButton.addActionListener(new ButtonListener());
        balanceButton.setPreferredSize(new Dimension(400,100));
        panel.add(balanceButton);

        JButton withdrawButton = new JButton("Withdraw From Account");
        withdrawButton.addActionListener(new ButtonListener());
        withdrawButton.setPreferredSize(new Dimension(400,100));
        panel.add(withdrawButton);

        JButton depositButton = new JButton("Deposit Into Account");
        depositButton.addActionListener(new ButtonListener());
        depositButton.setPreferredSize(new Dimension(400,100));
        panel.add(depositButton);

        JButton transferButton = new JButton("Transfer Money Between Accounts");
        transferButton.addActionListener(new ButtonListener());
        transferButton.setPreferredSize(new Dimension(400,100));
        panel.add(transferButton);

        // display the window.
        frame.setContentPane(panel);
        frame.pack();
        frame.setVisible(true);
    }
}

