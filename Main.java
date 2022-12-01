import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

//ButtonListener class implements ActionListener
//Has method actionPerformed which checks which button the user pressed and executes code accordingly, such as
//displaying all BankAccounts, viewing the balance of a particular BankAccount, creating a checking and savings
//account, depositing to and withdrawing from a BankAccount, and transferring money between two BankAccounts.
class ButtonListener implements ActionListener {
    //displays all the files in the accounts directory
    public static ArrayList<String> getAccountFiles(){
        File dir = new File("./accounts/");
        ArrayList<String> accountList = new ArrayList<String>(Arrays.asList(dir.list()));

        for (int i = 0; i < accountList.size(); i++){
            System.out.println((i +1) + ": " + accountList.get(i));
        }

        return accountList;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        //Display all accounts
        if (e.getActionCommand().equals("Display Accounts")) {
            ArrayList<BankAccount> accounts = Bank.getAccounts();
            ArrayList<Integer> accountNums = new ArrayList<>();
            String list = "";
            for (int i = 0; i < accounts.size(); i++){
                accountNums.add(accounts.get(i).getAccountNumber());
                list = list + accounts.get(i).toString() +"\n";
            }
            JOptionPane.showMessageDialog(null, list, "Display Accounts", JOptionPane.INFORMATION_MESSAGE);
        }

        //See account balance after inputting account number
        else if (e.getActionCommand().equals("See Account Balance")) {
            String text = JOptionPane.showInputDialog(null, "Enter your account number to view the balance: ", "See Account Balance", JOptionPane.INFORMATION_MESSAGE);
            try {
                JOptionPane.showMessageDialog(null, "Balance: " + Bank.getAccount(Integer.parseInt(text)).getMoney(), "View Balance", JOptionPane.INFORMATION_MESSAGE);
            } catch (NullPointerException | NumberFormatException n) {
                JOptionPane.showMessageDialog(null, "Not a valid account number.", "Error", JOptionPane.INFORMATION_MESSAGE);
            }
        }

        //Create a checking account
        else if (e.getActionCommand().equals("Create Checking Account")) {
            BankAccount acount = new BankAccount(AccountType.CHECKING, 0);
            Bank.addAccToArrayList(acount);
            try {
                Bank.save(acount);
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, "Cannot access account file of account number: " + acount.getAccountNumber(), "Failure to save account to file", JOptionPane.INFORMATION_MESSAGE);
            }
            JOptionPane.showMessageDialog(null, "Created checking account with number: " + acount.getAccountNumber(), "Success", JOptionPane.INFORMATION_MESSAGE);
        }

        //Create a savings account
        else if (e.getActionCommand().equals("Create Savings Account")) {
            BankAccount acount = new BankAccount(AccountType.SAVINGS, 0);
            Bank.addAccToArrayList(acount);
            try {
                Bank.save(acount);
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, "Cannot access account file of account number: " + acount.getAccountNumber(), "Failure to save account to file", JOptionPane.INFORMATION_MESSAGE);
            }
            JOptionPane.showMessageDialog(null, "Created savings account with number: " + acount.getAccountNumber(), "Success", JOptionPane.INFORMATION_MESSAGE);
        }

        //Withdraw from an account
        else if (e.getActionCommand().equals("Withdraw From Account")) {
            String text = JOptionPane.showInputDialog(null, "Enter your account number to withdraw from: ", "Withdraw", JOptionPane.INFORMATION_MESSAGE);
            String text2 = "";
            boolean isValidAccNum = false;
            try {
                int i = Integer.parseInt(text);
                isValidAccNum = Bank.isValidAccountNumber(i);
                if (!isValidAccNum) {
                    JOptionPane.showMessageDialog(null, "Not a valid account number.", "Error", JOptionPane.INFORMATION_MESSAGE);
                }
            } catch (NullPointerException | NumberFormatException n) {
                JOptionPane.showMessageDialog(null, "Not a valid account number.", "Error", JOptionPane.INFORMATION_MESSAGE);
            }
            if (text != null && isValidAccNum) {
                text2 = JOptionPane.showInputDialog(null, "Enter the amount to withdraw: ", "Withdraw", JOptionPane.INFORMATION_MESSAGE);
            }
            boolean isValidWithdrawAmount = false;
            if (isValidAccNum) {
                try {
                    int k = Integer.parseInt(text2);
                    if (k > -1) {
                        isValidWithdrawAmount = true;
                        if (k <= Bank.getAccount(Integer.parseInt(text)).getMoney()) {
                            Bank.getAccount(Integer.parseInt(text)).withdraw(Integer.parseInt(text2));
                            JOptionPane.showMessageDialog(null, "Amount Withdrawn", "Success", JOptionPane.INFORMATION_MESSAGE);
                        } else {
                            JOptionPane.showMessageDialog(null, "Cannot withdraw amount because there is not enough money in the bank.", "Failure to withdraw", JOptionPane.INFORMATION_MESSAGE);
                        }
                        Bank.save(Bank.getAccount(Integer.parseInt(text)));
                    } else {
                        JOptionPane.showMessageDialog(null, "Not a valid input.", "Error", JOptionPane.INFORMATION_MESSAGE);
                    }
                } catch (NullPointerException | NumberFormatException n) {
                    if (!isValidWithdrawAmount) {
                        JOptionPane.showMessageDialog(null, "Not a valid input.", "Error", JOptionPane.INFORMATION_MESSAGE);
                    }
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(null, "Could not access bank account.", "Error", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        }

        //Deposit into account
        else if (e.getActionCommand().equals("Deposit Into Account")) {
            String text = JOptionPane.showInputDialog(null, "Enter your account number to deposit into: ", "Deposit", JOptionPane.INFORMATION_MESSAGE);
            String text2 = "";
            boolean isValidAccNum = false;
            try {
                int i = Integer.parseInt(text);
                isValidAccNum = Bank.isValidAccountNumber(i);
                if (!isValidAccNum) {
                    JOptionPane.showMessageDialog(null, "Not a valid account number.", "Error", JOptionPane.INFORMATION_MESSAGE);
                }
            } catch (NullPointerException | NumberFormatException n) {
                JOptionPane.showMessageDialog(null, "Not a valid account number.", "Error", JOptionPane.INFORMATION_MESSAGE);
            }
            if (text != null && isValidAccNum) {
                text2 = JOptionPane.showInputDialog(null, "Enter the amount to deposit: ", "Deposit", JOptionPane.INFORMATION_MESSAGE);
            }
            boolean isValidDepositAmount = false;
            if (isValidAccNum) {
                try {
                    int k = Integer.parseInt(text2);
                    if (k > -1) {
                        isValidDepositAmount = true;
                        Bank.getAccount(Integer.parseInt(text)).deposit(Integer.parseInt(text2));
                        Bank.save(Bank.getAccount(Integer.parseInt(text)));
                        JOptionPane.showMessageDialog(null, "Amount Deposited", "Success", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(null, "Not a valid input.", "Error", JOptionPane.INFORMATION_MESSAGE);
                    }
                } catch (NullPointerException | NumberFormatException n) {
                    if (!isValidDepositAmount) {
                        JOptionPane.showMessageDialog(null, "Not a valid input.", "Error", JOptionPane.INFORMATION_MESSAGE);
                    }
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(null, "Could not access bank account.", "Error", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        }

        //Transfer money between accounts
        else if (e.getActionCommand().equals("Transfer Money Between Accounts")) {
            //Getting first account
            String text = JOptionPane.showInputDialog(null, "What account are you transferring from? (enter account number)", "Transfer", JOptionPane.INFORMATION_MESSAGE);
            boolean isValidAccNum = false;
            try {
                int i = Integer.parseInt(text);
                isValidAccNum = Bank.isValidAccountNumber(i);
                if (!isValidAccNum) {
                    JOptionPane.showMessageDialog(null, "Not a valid account number.", "Error", JOptionPane.INFORMATION_MESSAGE);
                }
            } catch (NullPointerException | NumberFormatException n) {
                JOptionPane.showMessageDialog(null, "Not a valid account number.", "Error", JOptionPane.INFORMATION_MESSAGE);
            }

            //Getting second account
            String text2 = "";
            if (text != null && isValidAccNum) {
                text2 = JOptionPane.showInputDialog(null, "What account are you transferring to? (enter account number)", "Transfer", JOptionPane.INFORMATION_MESSAGE);
                isValidAccNum = false;
                try {
                    int i = Integer.parseInt(text2);
                    isValidAccNum = Bank.isValidAccountNumber(i);
                    if (!isValidAccNum) {
                        JOptionPane.showMessageDialog(null, "Not a valid account number.", "Error", JOptionPane.INFORMATION_MESSAGE);
                    }
                } catch (NullPointerException | NumberFormatException n) {
                    JOptionPane.showMessageDialog(null, "Not a valid account number.", "Error", JOptionPane.INFORMATION_MESSAGE);
                }
            }

            //Getting transfer amount
            String text3 = "";
            if (text2 != null && isValidAccNum) {
                text3 = JOptionPane.showInputDialog(null, "Enter the amount you will be transferring", "Transfer", JOptionPane.INFORMATION_MESSAGE);
            }
            boolean isValidTransferAmount = false;
            if (isValidAccNum) {
                try {
                    int j = Integer.parseInt(text3);
                    if (j > -1) {
                        isValidTransferAmount = true;
                        if (j <= Bank.getAccount(Integer.parseInt(text)).getMoney()) {
                            Bank.getAccount(Integer.parseInt(text)).withdraw(Integer.parseInt(text3));
                            Bank.getAccount(Integer.parseInt(text2)).deposit(Integer.parseInt(text3));
                            JOptionPane.showMessageDialog(null, "Amount Transferred", "Success", JOptionPane.INFORMATION_MESSAGE);
                        } else {
                            JOptionPane.showMessageDialog(null, "Cannot withdraw amount because there is not enough money in the bank.", "Failure to withdraw and deposit", JOptionPane.INFORMATION_MESSAGE);
                        }
                        Bank.save(Bank.getAccount(Integer.parseInt(text)));
                        Bank.save(Bank.getAccount(Integer.parseInt(text2)));
                    } else {
                        JOptionPane.showMessageDialog(null, "Not a valid input.", "Error", JOptionPane.INFORMATION_MESSAGE);
                    }
                } catch (NullPointerException | NumberFormatException n) {
                    if (!isValidTransferAmount) {
                        JOptionPane.showMessageDialog(null, "Not a valid input.", "Error", JOptionPane.INFORMATION_MESSAGE);
                    }
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(null, "Could not access bank account.", "Error", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        }

    }
}

//Main class
//contains main method which creates JFrame, JPanel, and JButton objects
public class Main {
    public static void main(String[] args) {
        Bank bank = new Bank();
        try {
            Bank.addAlreadyExistingAccounts();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Could not access account files.", "Error", JOptionPane.INFORMATION_MESSAGE);
        }

        //TEST BANK ACCOUNT 1
        //I have been using these test accounts to test the buttons
        //NOTE: these accounts (including their respective values and files) will be overwritten each time to program starts.
        //In an actual, non-testing run of the program, this wouldn't be a problem because these test accounts wouldn't be here.
        BankAccount acc1 = new BankAccount(1000000, AccountType.CHECKING, 158);
        try {
            if (!Bank.isValidAccountNumber(acc1.getAccountNumber())) {
                Bank.addAccToArrayList(acc1);
            }
            Bank.save(acc1);
        } catch (IOException e) {
            System.out.println("IOException");
        }

        //TEST BANK ACCOUNT 2
        BankAccount acc2 = new BankAccount(1000001, AccountType.SAVINGS, 341);
        try {
            if (!Bank.isValidAccountNumber(acc2.getAccountNumber())) {
                Bank.addAccToArrayList(acc2);
            }
            Bank.save(acc2);
        } catch (IOException e) {
            System.out.println("IOException");
        }

        //TEST BANK ACCOUNT 3
        BankAccount acc3 = new BankAccount(1000002, AccountType.CHECKING, 200);
        try {
            if (!Bank.isValidAccountNumber(acc3.getAccountNumber())) {
                Bank.addAccToArrayList(acc3);
            }
            Bank.save(acc3);
        } catch (IOException e) {
            System.out.println("IOException");
        }

        // create and set up the window.
        JFrame frame = new JFrame("Bank Management");

        frame.setSize(600,600);

        // make the program close when the window closes
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //makes layout
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setLayout(new GridLayout(4, 2, 5, 5));
        panel.setMaximumSize(new Dimension(400, 400));
        panel.setBackground(Color.LIGHT_GRAY);
        frame.getContentPane().setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));

        //makes label for top of window
        JLabel label = new JLabel("Welcome to Bank Management");
        frame.getContentPane().add(label);
        frame.setBackground(new Color(218, 219, 217));

        Border blackLine = BorderFactory.createLineBorder(Color.BLACK);

        //makes each button for each option
        JButton displayButton = new JButton("Display Accounts");
        displayButton.addActionListener(new ButtonListener());
        displayButton.setPreferredSize(new Dimension(400,100));
        displayButton.setFont(new Font("Georgia", Font.PLAIN, 25));
        displayButton.setOpaque(true);
        displayButton.setBackground(new Color(154, 186, 128));
        displayButton.setForeground(Color.BLACK);
        displayButton.setBorder(blackLine);
        panel.add(displayButton);

        JButton balanceButton = new JButton("See Account Balance");
        balanceButton.addActionListener(new ButtonListener());
        balanceButton.setPreferredSize(new Dimension(400,100));
        balanceButton.setFont(new Font("Georgia", Font.PLAIN, 25));
        balanceButton.setOpaque(true);
        balanceButton.setBackground(new Color(154, 186, 128));
        balanceButton.setForeground(Color.BLACK);
        balanceButton.setBorder(blackLine);
        panel.add(balanceButton);

        JButton createCheckButton = new JButton("Create Checking Account");
        createCheckButton.addActionListener(new ButtonListener());
        createCheckButton.setPreferredSize(new Dimension(400,100));
        createCheckButton.setFont(new Font("Georgia", Font.PLAIN, 25));
        createCheckButton.setOpaque(true);
        createCheckButton.setBackground(new Color(154, 186, 128));
        createCheckButton.setForeground(Color.BLACK);
        createCheckButton.setBorder(blackLine);
        panel.add(createCheckButton);

        JButton createSavingsButton = new JButton("Create Savings Account");
        createSavingsButton.addActionListener(new ButtonListener());
        createSavingsButton.setPreferredSize(new Dimension(400,100));
        createSavingsButton.setFont(new Font("Georgia", Font.PLAIN, 25));
        createSavingsButton.setOpaque(true);
        createSavingsButton.setBackground(new Color(154, 186, 128));
        createSavingsButton.setForeground(Color.BLACK);
        createSavingsButton.setBorder(blackLine);
        panel.add(createSavingsButton);

        JButton withdrawButton = new JButton("Withdraw From Account");
        withdrawButton.addActionListener(new ButtonListener());
        withdrawButton.setPreferredSize(new Dimension(400,100));
        withdrawButton.setFont(new Font("Georgia", Font.PLAIN, 25));
        withdrawButton.setOpaque(true);
        withdrawButton.setBackground(new Color(154, 186, 128));
        withdrawButton.setForeground(Color.BLACK);
        withdrawButton.setBorder(blackLine);
        panel.add(withdrawButton);

        JButton depositButton = new JButton("Deposit Into Account");
        depositButton.addActionListener(new ButtonListener());
        depositButton.setPreferredSize(new Dimension(400,100));
        depositButton.setFont(new Font("Georgia", Font.PLAIN, 25));
        depositButton.setOpaque(true);
        depositButton.setBackground(new Color(154, 186, 128));
        depositButton.setForeground(Color.BLACK);
        depositButton.setBorder(blackLine);
        panel.add(depositButton);

        JButton transferButton = new JButton("Transfer Money Between Accounts");
        transferButton.addActionListener(new ButtonListener());
        transferButton.setPreferredSize(new Dimension(400,100));
        transferButton.setFont(new Font("Georgia", Font.PLAIN, 25));
        transferButton.setOpaque(true);
        transferButton.setBackground(new Color(154, 186, 128));
        transferButton.setForeground(Color.BLACK);
        transferButton.setBorder(blackLine);
        panel.add(transferButton);

        //exits window and closes it when button is pushed
        JButton exitButton = new JButton("Exit Program");
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                System.exit(0);
                frame.dispose();
                frame.setVisible(false);
            }
        }
        );
        exitButton.setPreferredSize(new Dimension(400,100));
        exitButton.setFont(new Font("Georgia", Font.PLAIN, 25));
        exitButton.setBorder(blackLine);
        exitButton.setOpaque(true);
        exitButton.setBackground(new Color(154, 186, 128));
        exitButton.setForeground(Color.BLACK);
        panel.add(exitButton);

        // display the window.
        frame.setContentPane(panel);
        frame.pack();
        frame.setVisible(true);
    }
}
