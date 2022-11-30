import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.io.PrintWriter;
import java.io.FileWriter;

public class Bank {
    private static ArrayList<BankAccount> accounts = new ArrayList<>();

    //Creates file directory called accounts if it isn't already there
    //Creates a .txt file named as the account's number if it isn't already there
    //Saves the account information in the file
    public static void save(BankAccount account) throws IOException {
        File file1 = new File("./accounts/");
        File file2 = new File("./accounts/" + account.getAccountNumber() + ".txt");
        file1.mkdir();
        file2.createNewFile();
        PrintWriter pw = new PrintWriter(new FileWriter(file2));
        pw.println(account.getType());
        pw.println(account.getAccountNumber());
        pw.println(account.getMoney());
        pw.println();
        pw.close();
        accounts.add(account);
    }

    public static BankAccount getAccount(int accNumber) {
        int value = -1;
        for (int i=0; i<accounts.size(); i++) {
            if (accounts.get(i).getAccountNumber() == accNumber) {
                value = i;
                break;
            }
        }
        if (value == -1) {
            return null;
        } else {
            return accounts.get(value);
        }
    }

    public static ArrayList<BankAccount> getAccounts() {
        return accounts;
    }

}
