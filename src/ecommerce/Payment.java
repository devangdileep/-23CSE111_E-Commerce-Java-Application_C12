package ecommerce;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Payment {

    private Long paymentId;
    private Order order;
    private BigDecimal amount;
    private String method;
    private String status;
    private String transactionId;
    private LocalDateTime paymentDate;

    public Payment(Long id, Order order, BigDecimal amount, String method) {
        this.paymentId = id;
        this.order = order;
        this.amount = amount;
        this.method = method;
        this.status = PaymentStatus.PENDING;
        this.paymentDate = LocalDateTime.now();
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public String getReceipt() {
        return "Receipt [Txn: " + transactionId + " | Amount: Rs. " + amount + " | Status: " + status + "]";
    }

}
