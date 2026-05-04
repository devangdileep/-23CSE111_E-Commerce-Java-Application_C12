package ecommerce;
import java.time.LocalDateTime;
public class Review {
    private Long reviewId;
    private Product product;
    private User user;
    private int rating;
    private String comment;
    private LocalDateTime createdAt;
    public Review(Long id, Product p, User u, int rating, String comment) {
        this.reviewId = id;
        this.product = p;
        this.user = u;
        this.rating = rating;
        this.comment = comment;
        this.createdAt = LocalDateTime.now();
    }
    public boolean isVerifiedPurchase() { return true; }
    public int getRating() { return rating; }
}
