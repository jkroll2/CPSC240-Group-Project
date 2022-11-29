import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class ButtonListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        //String text = field.getText();

        JOptionPane.showMessageDialog(null, "success");
    }
}

public class Main {
    public static void main(String[] args) {
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

