import core.CatalogFilterSystem;
import entity.Product;
import datastructure.array.ProductArray;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        CatalogFilterSystem system = new CatalogFilterSystem(100, 100);
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("Đang đọc dữ liệu từ file data.txt...");
        loadDataFromFile(system, "data.txt");
        System.out.println("Nạp dữ liệu hoàn tất!\n");

        while (true) {
            System.out.println("=== QUẢN LÝ DANH MỤC SẢN PHẨM ===");
            System.out.println("1. Thêm sản phẩm mới (Create)");
            System.out.println("2. Hiển thị tất cả sản phẩm (Read)");
            System.out.println("3. Cập nhật thông tin sản phẩm (Update)");
            System.out.println("4. Xóa sản phẩm (Delete)");
            System.out.println("5. Tìm kiếm sản phẩm theo ID");
            System.out.println("6. Lọc sản phẩm theo khoảng giá");
            System.out.println("7. Tìm kiếm tự động (Autocomplete)");
            System.out.println("8. Hiển thị Top K đánh giá cao nhất");
            System.out.println("0. Thoát chương trình");
            System.out.print("Chọn chức năng: ");
            
            String choice = scanner.nextLine();
            
            try {
                switch (choice) {
                    case "1":
                        System.out.print("Nhập ID: ");
                        String id1 = scanner.nextLine();
                        if (system.searchEngine.getById(id1) != null) {
                            System.out.println("Lỗi: ID đã tồn tại!");
                            break;
                        }
                        System.out.print("Nhập tên: ");
                        String name1 = scanner.nextLine();
                        System.out.print("Nhập giá: ");
                        double price1 = Double.parseDouble(scanner.nextLine());
                        System.out.print("Nhập đánh giá (1-5): ");
                        double rating1 = Double.parseDouble(scanner.nextLine());
                        system.addProduct(new Product(id1, name1, price1, rating1));
                        System.out.println("Thêm thành công!");
                        break;
                    case "2":
                        System.out.println("--- Danh sách sản phẩm ---");
                        printProductTable(system.getAllProducts());
                        break;
                    case "3":
                        System.out.print("Nhập ID cần cập nhật: ");
                        String id3 = scanner.nextLine();
                        Product oldP = system.searchEngine.getById(id3);
                        if (oldP == null) {
                            System.out.println("Không tìm thấy sản phẩm!");
                            break;
                        }
                        System.out.print("Nhập tên mới: ");
                        String name3 = scanner.nextLine();
                        System.out.print("Nhập giá mới: ");
                        double price3 = Double.parseDouble(scanner.nextLine());
                        System.out.print("Nhập đánh giá mới (1-5): ");
                        double rating3 = Double.parseDouble(scanner.nextLine());
                        system.updateProduct(id3, name3, price3, rating3);
                        System.out.println("Cập nhật thành công!");
                        break;
                    case "4":
                        System.out.print("Nhập ID cần xóa: ");
                        String id4 = scanner.nextLine();
                        if (system.deleteProduct(id4)) {
                            System.out.println("Đã xóa (Soft Delete) thành công!");
                        } else {
                            System.out.println("Không tìm thấy sản phẩm!");
                        }
                        break;
                    case "5":
                        System.out.print("Nhập ID: ");
                        String id5 = scanner.nextLine();
                        Product p5 = system.searchEngine.getById(id5);
                        printSingleProduct(p5);
                        break;
                    case "6":
                        System.out.print("Nhập giá nhỏ nhất: ");
                        double minPrice = Double.parseDouble(scanner.nextLine());
                        System.out.print("Nhập giá lớn nhất: ");
                        double maxPrice = Double.parseDouble(scanner.nextLine());
                        printProductTable(system.filterByPrice(minPrice, maxPrice));
                        break;
                    case "7":
                        System.out.print("Nhập từ khóa bắt đầu (Prefix): ");
                        String prefix = scanner.nextLine();
                        printProductTable(system.autocomplete(prefix));
                        break;
                    case "8":
                        System.out.print("Nhập số lượng K cần lấy: ");
                        int k = Integer.parseInt(scanner.nextLine());
                        printProductTable(system.getTopRated(k));
                        break;
                    case "0":
                        System.out.println("Tạm biệt!");
                        scanner.close();
                        return;
                    default:
                        System.out.println("Lựa chọn không hợp lệ!");
                }
            } catch (Exception e) {
                System.out.println("Lỗi nhập liệu! Vui lòng thử lại.");
            }
            System.out.println();
        }
    }

    private static void printProductTable(datastructure.array.ProductArray products) {
        if (products.size == 0) {
            System.out.println("Không có sản phẩm nào để hiển thị.");
            return;
        }
        System.out.println("+--------+------------------------------------------+------------+----------+");
        System.out.printf("| %-6s | %-40s | %-10s | %-8s |\n", "ID", "Tên Sản Phẩm", "Giá ($)", "Đánh giá");
        System.out.println("+--------+------------------------------------------+------------+----------+");
        for (Product p : products) {
            String name = p.getName();
            if (name.length() > 40) {
                name = name.substring(0, 37) + "...";
            }
            System.out.printf("| %-6s | %-40s | %-10.2f | %-8.1f |\n", p.getId(), name, p.getPrice(), p.getRating());
        }
        System.out.println("+--------+------------------------------------------+------------+----------+");
    }

    private static void printSingleProduct(Product p) {
        if (p == null) {
            System.out.println("Không tìm thấy sản phẩm!");
            return;
        }
        datastructure.array.ProductArray arr = new datastructure.array.ProductArray();
        arr.add(p);
        printProductTable(arr);
    }

    private static void loadDataFromFile(CatalogFilterSystem system, String filename) {
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 4) {
                    String id = parts[0].trim();
                    String name = parts[1].trim();
                    double price = Double.parseDouble(parts[2].trim());
                    double rating = Double.parseDouble(parts[3].trim());
                    system.addProduct(new Product(id, name, price, rating));
                }
            }
        } catch (IOException e) {
            System.out.println("Lỗi đọc file hoặc file không tồn tại. Đang chạy bằng dữ liệu trống.");
        }
    }
}
