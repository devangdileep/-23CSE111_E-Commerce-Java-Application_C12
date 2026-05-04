package ecommerce;
public class MockPaymentGateway extends PaymentGateway {
    public boolean processPayment(Payment payment) {
        payment.setStatus(PaymentStatus.SUCCESS);
        payment.setTransactionId("TXN-" + System.currentTimeMillis());
        System.out.println(payment.getReceipt());
        return true;
    }
}
