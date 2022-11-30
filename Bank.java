import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.io.PrintWriter;
import java.io.FileWriter;

//Bank class
//Defines a Bank which stores BankAccounts into an ArrayList
//Has methods to create a Bank, save a BankAccount, add a BankAccount to the ArrayList,
//get a BankAccount given the account number, get the ArrayList of BankAccounts, and check
//to see if an account number entered by the user belongs to a BankAccount
public class Bank {
    private static ArrayList<BankAccount> accounts;

    //initializes the arraylist and opens a bank object
    public Bank(){
        accounts = new ArrayList<>();
    }

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

    //Add a BankAccount to the ArrayList
    public static void addAccToArrayList(BankAccount account) {
        accounts.add(account);
    }

    //Get a BankAccount given the account number
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

    //Get the ArrayList of BankAccounts
    public static ArrayList<BankAccount> getAccounts() {
        return accounts;
    }

    //Checks whether an account number entered by the user actually belongs to a BankAccount
    public static boolean isValidAccountNumber(int accNumber) {
        boolean isValid = false;
        for (int i=0; i<accounts.size(); i++) {
            if (accounts.get(i).getAccountNumber() == accNumber) {
                isValid = true;
                break;
            }
        }
        return isValid;
    }

}
