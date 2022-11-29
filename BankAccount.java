public class BankAccount {
    private int account_number;
    private int money;
    private  AccountType type;
    private int nextNumber = 1000000;

    public BankAccount(){
        account_number = nextNumber ;
        nextNumber++;
        money = 0;



    }
}
