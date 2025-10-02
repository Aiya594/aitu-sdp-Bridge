import provider.MasterCard;
import provider.Visa;
import type.IPaymentType;
import type.OnlinePayment;
import user.BankAccount;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        System.out.println("Example1:");
        test1();
        System.out.println("""
        ---------------------------------
        Example2:
        """);
        test2();
    }

    private static void test1(){
        double balance=1000;
        double price1=10;
        double price2=900.99;
        BankAccount user=new BankAccount(balance);
        IPaymentType type=new OnlinePayment(user,new MasterCard());
        type.processPayment(price1);
        type.refundPayment(price1);
        type.processPayment(price2);
    }

    private static void test2(){
        double balance=1000;
        double price1=30.99;
        double price2=990;
        BankAccount user=new BankAccount(balance);

        IPaymentType type=new OnlinePayment(user,new Visa());
        type.processPayment(price1);
        type.processPayment(price2);
    }


}