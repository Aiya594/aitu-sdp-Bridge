package type;

import provider.IPaymentProvider;
import user.BankAccount;

public class SubscriptionPayment implements IPaymentType{
    private final BankAccount user;
    private final IPaymentProvider provider;

    public SubscriptionPayment(BankAccount user, IPaymentProvider provider) {
        this.user = user;
        this.provider = provider;
    }

    @Override
    public void processPayment(double sum) {
        provider.capture(user,sum);
        System.out.println(System.out.printf("""
                Subscription payment succeeded via %s
                Your balance is: %.2f
                """,provider.getName(), user.getBalance()));
    }

    @Override
    public void refundPayment(double sum) {
        provider.refund(user,sum);
        System.out.printf("""
                Subscription refund succeeded, check you bank account.
                Balance:%.2f
                """,user.getBalance());
    }
}
