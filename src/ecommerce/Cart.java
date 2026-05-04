package ecommerce;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Cart {

    private Long cartId;
    private User user;
    private List<CartItem> items;
    private LocalDateTime createdAt;

    public Cart(Long id, User user) {
        this.cartId = id;
        this.user = user;
        this.items = new ArrayList<>();
        this.createdAt = LocalDateTime.now();
    }

    public void addItem(Product product, int quantity) {
        for (CartItem item : items) {
            if (item.getProduct().equals(product)) {
                item.increaseQuantity(quantity);
                return;
            }
        }
        items.add(new CartItem(System.currentTimeMillis(), product, quantity));
    }

    public BigDecimal getTotalAmount() {
        BigDecimal total = BigDecimal.ZERO;
        for (CartItem item : items) {
            total = total.add(item.getSubtotal());
        }
        return total;
    }

    public void clearCart() {
        items.clear();
    }

    public Order checkout(Address address, PaymentGateway gateway) throws Exception {

        if (items.isEmpty()) {
            throw new Exception("Cart is empty");
        }

        List<OrderItem> orderItems = new ArrayList<>();

        for (CartItem item : items) {
            item.getProduct().reduceStock(item.getQuantity());
            orderItems.add(new OrderItem(System.currentTimeMillis(), item.getProduct(), item.getQuantity(), item.getProduct().getPrice()));
        }

        Order order = new Order(System.currentTimeMillis(), user, orderItems, address);

        Payment payment = new Payment(System.currentTimeMillis(), order, getTotalAmount(), PaymentMethod.CREDIT_CARD);

        if (gateway.processPayment(payment)) {
            order.updateStatus(OrderStatus.PROCESSING);
            order.setPayment(payment);
            user.addOrder(order);
            clearCart();
            return order;
        }

        throw new Exception("Payment failed");
    }

}
