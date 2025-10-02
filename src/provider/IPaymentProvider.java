package provider;

import user.BankAccount;

public interface IPaymentProvider {
    void capture(BankAccount user,double sum);
    void refund(BankAccount user,double sum);
    String getName();
}
