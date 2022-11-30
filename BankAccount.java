//BankAccount class
//Contains constructors and methods to get an account number, get the AccountType
//of a BankAccount, get the balance of a BankAccount, deposit and withdraw money into
//a BankAccount, and print the BankAccount object in a readable format.
public class BankAccount {
    private int accountNumber;
    private int money;
    private  AccountType type;
    private static int nextNumber = 1000000;

    //created default empty bank account
    public BankAccount() {
        accountNumber = nextNumber;
        nextNumber++;
        type = null;
        money = 0;
    }
    //Non-default constructor for BankAccount
    //Takes in AccountType and amount of money
    //Sets the account number, type, and money
    public BankAccount(AccountType type, int money){
        this.accountNumber = nextNumber;
        nextNumber++;
        this.type = type;
        this.money = money;
    }
    //Returns the account number of a bank account
    public int getAccountNumber(){
        return accountNumber;
    }
    //Returns the bank account type
    public AccountType getType(){
        return type;
    }
    //Returns the balance of a bank account
    public int getMoney(){
        return money;
    }
    //Deposits money into a bank account
    public int deposit(int amount){
        money = money + amount;
        return money;
    }
    //Withdraws money from a bank account
    public int withdraw(int amount){
        money = money - amount;
        return money;
    }

    //prints account object in readable format
    @Override
    public String toString(){
        return "Account Number: " + this.getAccountNumber() + " -- Type: " + this.getType();
    }
}
