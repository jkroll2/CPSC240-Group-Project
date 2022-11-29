import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.io.PrintWriter;
import java.io.FileWriter;

public class Bank {
    private ArrayList<BankAccount> accounts = new ArrayList<>();

    //I'm trying to make this a bit like Assignment 3
    //the name of the files the accounts' details will be stored in is just the account number, and
    //they will be in a directory called 'accounts'
    public void save(BankAccount account) throws IOException {
        File file1 = new File("./accounts/");
        File file2 = new File("./accounts/" + account.getAccountNumber());
        file1.mkdir();
        file2.createNewFile();
        PrintWriter pw = new PrintWriter(new FileWriter(String.valueOf(account.getAccountNumber())));
        pw.println(account.getType());
        pw.println(account.getAccountNumber());
        pw.println(account.getMoney());
        pw.close();
    }


}
