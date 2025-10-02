package provider;

import user.BankAccount;

public class MasterCard implements IPaymentProvider {
    @Override
    public void capture(BankAccount user,double sum) {
        user.debit(sum);
    }

    @Override
    public void refund(BankAccount user,double sum) {
        user.refund(sum);
    }

    @Override
    public String getName() {
        return "MasterCard";
    }
}
