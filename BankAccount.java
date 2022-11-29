public class BankAccount {
    private int accountNumber;
    private int money;
    private  AccountType type;
    private int nextNumber = 1000000;

    public BankAccount(int accountNumber, AccountType type, int money){
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
}
