package ecommerce;
import java.math.BigDecimal;
public class DiscountedProduct extends Product {
    private double discountPercentage;
    public DiscountedProduct(Long id, String name, String desc, BigDecimal price, int stock, Category category, double discount) {
        super(id, name, desc, price, stock, category);
        this.discountPercentage = discount;
    }
    public BigDecimal getPrice() {
        BigDecimal original = super.getPrice();
        BigDecimal discount = original.multiply(BigDecimal.valueOf(discountPercentage / 100));
        return original.subtract(discount);
    }
}
