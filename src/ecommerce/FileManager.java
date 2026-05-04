package ecommerce;

import java.io.*;
import java.util.List;

public class FileManager {

    public static void saveProducts(List<Product> products, String filename) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filename))) {

            for (Product p : products) {
                String line = p.getProductId() + "|" + p.getName() + "|" + p.getDescription() + "|" + 
                             p.getPrice() + "|" + p.getStockQuantity() + "|" + p.getCategoryName();
                bw.write(line);
                bw.newLine();
            }

        } catch (IOException e) {
            System.out.println("Error saving products: " + e.getMessage());
        }
    }

    public static void saveUser(User user, String filename) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filename))) {

            String line = user.getUserId() + "|" + user.getName() + "|" + user.getEmail() + "|" + 
                         user.getRole() + "|" + user.getAddressString();
            bw.write(line);
            bw.newLine();

        } catch (IOException e) {
            System.out.println("Error saving user: " + e.getMessage());
        }
    }

    public static void saveOrders(List<Order> orders, String filename) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filename, true))) {

            for (Order o : orders) {
                bw.write(o.getInvoice());
                bw.write("---");
                bw.newLine();
            }

        } catch (IOException e) {
            System.out.println("Error saving orders: " + e.getMessage());
        }
    }

    public static void readOrders(String filename) {
        File f = new File(filename);
        if (!f.exists()) {
            return;
        }

        try (BufferedReader br = new BufferedReader(new FileReader(f))) {

            String line;
            while ((line = br.readLine()) != null) {
                if (line.equals("---")) {
                    System.out.println("------------------");
                } else {
                    System.out.println(line);
                }
            }

        } catch (IOException e) {
            System.out.println("Error reading orders: " + e.getMessage());
        }
    }

    public static void saveLogs(String logMsg, String filename) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filename, true))) {

            bw.write(logMsg);
            bw.newLine();

        } catch (IOException e) {
            System.out.println("Error saving log: " + e.getMessage());
        }
    }

    public static void readLogs(String filename) {
        File f = new File(filename);
        if (!f.exists()) {
            return;
        }

        try (BufferedReader br = new BufferedReader(new FileReader(f))) {

            String line;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }

        } catch (IOException e) {
            System.out.println("Error reading logs: " + e.getMessage());
        }
    }

}
