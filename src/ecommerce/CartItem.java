package ecommerce;
import java.math.BigDecimal;
public class CartItem {
    private Long cartItemId;
    private Product product;
    private int quantity;
    public CartItem(Long id, Product p, int q) {
        this.cartItemId = id;
        this.product = p;
        this.quantity = q;
    }
    public BigDecimal getSubtotal() {
        return product.getPrice().multiply(BigDecimal.valueOf(quantity));
    }
    public void increaseQuantity(int amount) { this.quantity += amount; }
    public void decreaseQuantity(int amount) {
        if(this.quantity >= amount) this.quantity -= amount;
    }
    public Product getProduct() { return product; }
    public int getQuantity() { return quantity; }
}
