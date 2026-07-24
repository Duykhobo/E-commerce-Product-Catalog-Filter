package utils;

import entity.Product;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileUtils {

    public static List<Product> readProductsFromFile(String filePath) {
        List<Product> products = new ArrayList<>();
        try ( BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.trim().isEmpty() || line.startsWith("#")) {
                    continue;
                }
                String[] data = line.split(",");
                if (data.length >= 4) {
                    try {
                        String id = data[0].trim();
                        String name = data[1].trim();
                        double price = Double.parseDouble(data[2].trim());
                        double rating = Double.parseDouble(data[3].trim());
                        Product p = new Product(id, name, price, rating);
                        if (data.length >= 5) {
                            p.setActive(Boolean.parseBoolean(data[4].trim()));
                        }
                        products.add(p);
                    } catch (IllegalArgumentException e) {
                        System.err.println("Bo qua dong loi du lieu: " + line + " - Loi: " + e.getMessage());
                    }
                }
            }
        } catch (IOException e) {
            System.err.println("Lỗi khi đọc file: " + e.getMessage());
        }
        return products;
    }

    public static void writeProductsToFile(String filePath, List<Product> products) {
        try ( BufferedWriter bw = new BufferedWriter(new FileWriter(filePath))) {
            for (Product p : products) {
                if (p != null) {
                    bw.write(String.format("%s,%s,%.2f,%.1f,%b", p.getId(), p.getName(), p.getPrice(), p.getRating(), p.isActive()));
                    bw.newLine();
                }
            }
        } catch (IOException e) {
            System.err.println("Lỗi khi ghi file: " + e.getMessage());
        }
    }
}
