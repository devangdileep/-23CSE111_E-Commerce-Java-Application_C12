package ecommerce;
import java.math.BigDecimal;
public class OrderItem {
    private Long orderItemId;
    private Product product;
    private int quantity;
    private BigDecimal priceAtPurchase;
    public OrderItem(Long id, Product p, int q, BigDecimal price) {
        this.orderItemId = id;
        this.product = p;
        this.quantity = q;
        this.priceAtPurchase = price;
    }
    public BigDecimal getSubtotal() {
        return priceAtPurchase.multiply(BigDecimal.valueOf(quantity));
    }
    public String getProductSnapshot() {
        return product.getName() + " (" + quantity + ") @ Rs. " + priceAtPurchase;
    }
}
