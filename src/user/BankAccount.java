package user;

public class BankAccount {

    private double balance;

    public BankAccount(double balance){
        this.balance=balance;
    }

    public double getBalance(){
        return balance;
    }

    public void debit(double sum){
        if (balance>=sum){
            balance-=sum;
        } else {
            System.out.println("Insufficient funds");
            System.exit(0);
        }

    }

    public void refund(double sum){
        balance+=sum;
    }
}
