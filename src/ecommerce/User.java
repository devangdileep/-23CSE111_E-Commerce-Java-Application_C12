package ecommerce;

import java.util.ArrayList;
import java.util.List;

public class User {

    private Long userId;
    private String name;
    private String email;
    private String passwordHash;
    private String role;
    private Address address;
    private List<Order> orderHistory;

    public User(Long id, String name, String email, String password, String role, Address address) {
        this.userId = id;
        this.name = name;
        this.email = email;
        this.passwordHash = hashPassword(password);
        this.role = role;
        this.address = new Address(address);
        this.orderHistory = new ArrayList<>();
    }

    private String hashPassword(String pwd) {
        return pwd + "_hash";
    }

    public boolean login(String email, String password) throws InvalidLoginException {
        if (this.email.equals(email) && this.passwordHash.equals(hashPassword(password))) {
            return true;
        }
        throw new InvalidLoginException("Login failed");
    }

    public void updateProfile(String name, Address address) {
        this.name = name;
        this.address = address;
    }

    public List<Order> getOrderHistory() {
        return orderHistory;
    }

    public void addOrder(Order order) {
        this.orderHistory.add(order);
    }

    public Long getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getRole() {
        return role;
    }

    public String getAddressString() {
        if (address != null) {
            return address.getFullAddress();
        }
        return "None";
    }

}
