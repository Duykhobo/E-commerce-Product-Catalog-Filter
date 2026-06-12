import core.CatalogFilterSystem;
import entity.Product;

public class App {
    public static void main(String[] args) {
        System.out.println("=== E-commerce Product Catalog Filter ===");
        
        CatalogFilterSystem system = new CatalogFilterSystem(100);

        // Tạo sẵn một số sản phẩm giả định để test
        Product p1 = new Product("P001", "laptop dell xps", 1500.0, 4.8);
        Product p2 = new Product("P002", "iphone 14 pro", 1200.0, 4.9);
        Product p3 = new Product("P003", "sony headphones", 350.0, 4.7);
        
        // TODO: Sinh viên hoàn thiện các cấu trúc trước rồi bỏ comment để test
        // system.addProduct(p1);
        // system.addProduct(p2);
        // system.addProduct(p3);
        
        System.out.println("Vui lòng hoàn thiện mã nguồn các Engine.");
    }
}
