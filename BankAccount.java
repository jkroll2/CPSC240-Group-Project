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
    public BankAccount(AccountType type, int money){
        this.accountNumber = nextNumber;
        nextNumber++;
        this.type = type;
        this.money = money;
    }
    public int getAccountNumber(){
        return accountNumber;
    }
    public AccountType getType(){
        return type;
    }
    public int getMoney(){
        return money;
    }
    public int deposit(int amount){
        money = money + amount;
        return money;
    }
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
