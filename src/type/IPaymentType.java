package type;

public interface IPaymentType {
    void processPayment(double sum);
    void refundPayment(double sum);
}
