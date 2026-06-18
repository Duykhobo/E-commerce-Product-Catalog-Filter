import core.CatalogFilterSystem;
import entity.Product;

public class App {
    public static void main(String[] args) {
        System.out.println("=== E-commerce Product Catalog Filter ===");
        
        CatalogFilterSystem system = new CatalogFilterSystem(100, 100);

        // Tạo dữ liệu giả lập (Seed data)
        Product p1 = new Product("P001", "Laptop Dell XPS 15", 1500.0, 4.8);
        Product p2 = new Product("P002", "iPhone 14 Pro", 1200.0, 4.9);
        Product p3 = new Product("P003", "Sony Headphones WH-1000XM5", 350.0, 4.7);
        Product p4 = new Product("P004", "Laptop Asus ROG Zephyrus", 1800.0, 4.5);
        Product p5 = new Product("P005", "MacBook Air M2", 1100.0, 4.9);
        Product p6 = new Product("P006", "iPad Pro 11-inch", 800.0, 4.8);
        Product p7 = new Product("P007", "Logitech MX Master 3S", 100.0, 4.6);
        Product p8 = new Product("P008", "Mechanical Keyboard Keychron Q1", 170.0, 4.4);
        Product p9 = new Product("P009", "Samsung Galaxy S23 Ultra", 1300.0, 4.8);
        Product p10 = new Product("P010", "Apple Watch Series 8", 400.0, 4.7);

        // Thêm vào hệ thống
        System.out.println("Đang nạp dữ liệu vào hệ thống...");        system.addProduct(p1);
        system.addProduct(p2);
        system.addProduct(p3);
        system.addProduct(p4);
        system.addProduct(p5);
        system.addProduct(p6);
        system.addProduct(p7);
        system.addProduct(p8);
        system.addProduct(p9);
        system.addProduct(p10);
        System.out.println("Nạp dữ liệu hoàn tất!\n");
        
        // 1. Test tính năng lấy Top Rated (Heap)
        System.out.println("--- Top 3 sản phẩm đánh giá cao nhất (Max-Heap) ---");
        for (Product p : system.getTopRated(3)) {
            System.out.println(p);
        }
        
        // 2. Test tính năng tìm kiếm theo Prefix (Trie)
        System.out.println("\n--- Kết quả tìm kiếm Autocomplete cho 'lap' (Trie) ---");
        for (Product p : system.autocomplete("lap")) {
            System.out.println(p);
        }

        System.out.println("\n--- Kết quả tìm kiếm Autocomplete cho 'mac' (Trie) ---");
        for (Product p : system.autocomplete("mac")) {
            System.out.println(p);
        }
        
        // 3. Test tính năng lọc giá (BST)
        System.out.println("\n--- Các sản phẩm có giá từ 1000 đến 1500 (BST) ---");
        for (Product p : system.filterByPrice(1000, 1500)) {
            System.out.println(p);
        }

        // 4. Test tìm kiếm bằng Hash Table (ID)
        System.out.println("\n--- Tìm kiếm sản phẩm bằng ID (Hash Table) ---");
        System.out.println("Tìm ID 'P007': " + system.searchEngine.getById("P007"));
        System.out.println("Tìm ID 'P999' (không tồn tại): " + system.searchEngine.getById("P999"));
    }
}
