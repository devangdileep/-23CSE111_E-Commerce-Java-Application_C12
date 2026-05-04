package ecommerce;

import java.math.BigDecimal;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        String logFile = "logs.txt";
        String orderFile = "orders.txt";
        String productFile = "products.txt";
        String userFile = "user_data.txt";

        FileManager.saveLogs("App Started", logFile);

        Scanner sc = new Scanner(System.in);

        Address adr = new Address(1L, "123 Amrita St", "Coimbatore", "TN", "641112", "India");

        User user = new User(1L, "Student", "student@amrita.edu", "pass123", Role.CUSTOMER, adr);

        Category cat = new Category(1L, "Electronics", null);

        Product p1 = new Product(1L, "Laptop", "Gaming", new BigDecimal("1200.00"), 5, cat);

        DiscountedProduct p2 = new DiscountedProduct(2L, "Phone", "Smart", new BigDecimal("800.00"), 10, cat, 10.0);

        List<Product> products = new ArrayList<>();
        products.add(p1);
        products.add(p2);

        Cart cart = new Cart(1L, user);

        PaymentGateway gateway = new MockPaymentGateway();

        boolean run = true;

        while(run) {

            System.out.println("\n========== E-COMMERCE MENU ==========");
            System.out.println("1. View Products");
            System.out.println("2. Add Laptop to Cart");
            System.out.println("3. Add Phone to Cart");
            System.out.println("4. Checkout");
            System.out.println("5. View Saved Orders");
            System.out.println("6. View Logs");
            System.out.println("7. Exit");
            System.out.print("Select an option: ");

            int c = -1;
            if (sc.hasNextInt()) {
                c = sc.nextInt();
            } else {
                sc.next();
                continue;
            }

            try {

                switch(c) {

                    case 1:
                        System.out.println("1. " + p1.getName() + " - Rs. " + p1.getPrice());
                        System.out.println("2. " + p2.getName() + " - Rs. " + p2.getPrice());
                        break;

                    case 2:
                        cart.addItem(p1, 1);
                        System.out.println("Added Laptop to cart!");
                        break;

                    case 3:
                        cart.addItem(p2, 1);
                        System.out.println("Added Phone to cart!");
                        break;

                    case 4:
                        Order o = cart.checkout(adr, gateway);
                        System.out.println(o.getInvoice());

                        List<Order> ol = new ArrayList<>();
                        ol.add(o);

                        FileManager.saveOrders(ol, orderFile);
                        FileManager.saveLogs("Order placed: Rs. " + o.calculateTotal(), logFile);
                        break;

                    case 5:
                        FileManager.readOrders(orderFile);
                        break;

                    case 6:
                        FileManager.readLogs(logFile);
                        break;

                    case 7:
                        run = false;
                        FileManager.saveProducts(products, productFile);
                        FileManager.saveUser(user, userFile);
                        System.out.println("Data saved. Exiting...");
                        break;

                    default:
                        System.out.println("Invalid choice. Try again.");

                }

            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }

        }

        sc.close();

    }

}
