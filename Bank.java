import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.io.PrintWriter;
import java.io.FileWriter;

public class Bank {
    private ArrayList<BankAccount> accounts = new ArrayList<>();

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
    }


}
