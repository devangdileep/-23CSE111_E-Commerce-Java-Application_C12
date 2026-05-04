package ecommerce;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Product {

    private Long productId;
    private String name;
    private String description;
    private BigDecimal price;
    private int stockQuantity;
    private Category category;
    private List<Review> reviews;

    public static int totalProductsCreated = 0;

    public Product(Long id, String name, String desc, BigDecimal price, int stock, Category category) {
        this.productId = id;
        this.name = name;
        this.description = desc;
        this.price = price;
        this.stockQuantity = stock;
        this.category = category;
        this.reviews = new ArrayList<>();
        totalProductsCreated++;
    }

    public boolean isInStock() {
        return stockQuantity > 0;
    }

    public void updatePrice(BigDecimal newPrice) {
        this.price = newPrice;
    }

    public void updatePrice(double newPrice) {
        this.price = BigDecimal.valueOf(newPrice);
    }

    public void reduceStock(int quantity) throws OutOfStockException {
        if (this.stockQuantity < quantity) {
            throw new OutOfStockException("Not enough stock: " + name);
        }
        this.stockQuantity -= quantity;
    }

    public void restockInventory(int quantity) {
        this.stockQuantity += quantity;
    }

    public double getAverageRating() {
        if (reviews == null || reviews.isEmpty()) {
            return 0.0;
        }

        double sum = 0;
        for (Review r : reviews) {
            sum += r.getRating();
        }
        return sum / reviews.size();
    }

    public void addReview(Review review) {
        if (this.reviews == null) {
            this.reviews = new ArrayList<>();
        }
        this.reviews.add(review);
    }

    public Long getProductId() {
        return productId;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public int getStockQuantity() {
        return stockQuantity;
    }

    public String getCategoryName() {
        if (category != null) {
            return category.getName();
        }
        return "None";
    }

}
