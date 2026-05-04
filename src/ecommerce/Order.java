package ecommerce;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public class Order {

    private Long orderId;
    private User user;
    private List<OrderItem> items;
    private LocalDateTime orderDate;
    private String status;
    private BigDecimal totalAmount;
    private Address shippingAddress;
    private Payment payment;

    public Order(Long id, User u, List<OrderItem> items, Address address) {
        this.orderId = id;
        this.user = u;
        this.items = items;
        this.orderDate = LocalDateTime.now();
        this.status = OrderStatus.PENDING;
        this.shippingAddress = address;
        this.totalAmount = calculateTotal();
    }

    public void updateStatus(String status) {
        this.status = status;
    }

    public BigDecimal calculateTotal() {
        BigDecimal total = BigDecimal.ZERO;
        if(items != null) {
            for (OrderItem item : items) {
                total = total.add(item.getSubtotal());
            }
        }
        return total;
    }

    public String getInvoice() {
        StringBuilder sb = new StringBuilder();
        sb.append("Order #").append(orderId).append(" | Total: Rs. ").append(totalAmount).append("\n");
        sb.append("User: ").append(user.getName()).append("\n");
        sb.append("Status: ").append(status).append("\n");

        for (OrderItem item : items) {
            sb.append("- ").append(item.getProductSnapshot()).append("\n");
        }

        return sb.toString();
    }

    public void setPayment(Payment p) {
        this.payment = p;
    }

    public String getStatus() {
        return status;
    }

}
