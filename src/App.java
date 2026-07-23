import core.CatalogFilterSystem;
import entity.Product;
import datastructure.array.ProductArray;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class App {
    // ANSI Colors for beautiful UI
    public static final String RESET = "\033[0m";
    public static final String RED = "\033[1;31m";
    public static final String GREEN = "\033[1;32m";
    public static final String YELLOW = "\033[1;33m";
    public static final String BLUE = "\033[1;34m";
    public static final String CYAN = "\033[1;36m";
    public static final String PURPLE = "\033[1;35m";
    public static final String BOLD = "\033[1m";

    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public static void main(String[] args) {
        CatalogFilterSystem system = new CatalogFilterSystem(100, 100);
        Scanner scanner = new Scanner(System.in);
        
        clearScreen();
        System.out.println(CYAN + BOLD + "╔════════════════════════════════════════════════════════╗" + RESET);
        System.out.println(CYAN + BOLD + "║        ĐANG KHỞI ĐỘNG HỆ THỐNG E-COMMERCE...           ║" + RESET);
        System.out.println(CYAN + BOLD + "╚════════════════════════════════════════════════════════╝" + RESET);
        
        System.out.println(YELLOW + "=> Đang đọc dữ liệu từ file data.txt..." + RESET);
        loadDataFromFile(system, "data.txt");
        System.out.println(GREEN + "=> Nạp dữ liệu hoàn tất! Nhấn Enter để tiếp tục..." + RESET);
        scanner.nextLine();

        while (true) {
            clearScreen();
            System.out.println(PURPLE + BOLD + "    ███████╗ ██████╗ ██████╗ ███╗   ███╗███╗   ███╗███████╗██████╗  ██████╗███████╗");
            System.out.println("    ██╔════╝██╔════╝██╔═══██╗████╗ ████║████╗ ████║██╔════╝██╔══██╗██╔════╝██╔════╝");
            System.out.println("    █████╗  ██║     ██║   ██║██╔████╔██║██╔████╔██║█████╗  ██████╔╝██║     █████╗  ");
            System.out.println("    ██╔══╝  ██║     ██║   ██║██║╚██╔╝██║██║╚██╔╝██║██╔══╝  ██╔══██╗██║     ██╔══╝  ");
            System.out.println("    ███████╗╚██████╗╚██████╔╝██║ ╚═╝ ██║██║ ╚═╝ ██║███████╗██║  ██║╚██████╗███████╗");
            System.out.println("    ╚══════╝ ╚═════╝ ╚═════╝ ╚═╝     ╚═╝╚═╝     ╚═╝╚══════╝╚═╝  ╚═╝ ╚═════╝╚══════╝" + RESET);
            
            System.out.println("\n" + CYAN + BOLD + "╔════════════════════════ QUẢN LÝ DANH MỤC ═══════════════════════╗" + RESET);
            System.out.println(CYAN + "║ " + YELLOW + "[1]" + RESET + " Thêm sản phẩm mới (Create)                                  " + CYAN + "║");
            System.out.println(CYAN + "║ " + YELLOW + "[2]" + RESET + " Hiển thị tất cả sản phẩm (Read)                             " + CYAN + "║");
            System.out.println(CYAN + "║ " + YELLOW + "[3]" + RESET + " Cập nhật thông tin sản phẩm (Update)                        " + CYAN + "║");
            System.out.println(CYAN + "║ " + YELLOW + "[4]" + RESET + " Xóa sản phẩm (Delete)                                       " + CYAN + "║");
            System.out.println(CYAN + "║ " + YELLOW + "[5]" + RESET + " Tìm kiếm sản phẩm theo ID " + GREEN + "[Hash Table]" + RESET + "                      " + CYAN + "║");
            System.out.println(CYAN + "║ " + YELLOW + "[6]" + RESET + " Lọc sản phẩm theo khoảng giá " + GREEN + "[BST]" + RESET + "                          " + CYAN + "║");
            System.out.println(CYAN + "║ " + YELLOW + "[7]" + RESET + " Tìm kiếm tự động (Autocomplete) " + GREEN + "[Array]" + RESET + "                     " + CYAN + "║");
            System.out.println(CYAN + "║ " + YELLOW + "[8]" + RESET + " Lọc sản phẩm theo Rating (Exact Match) " + GREEN + "[Hash Table]" + RESET + "        " + CYAN + "║");
            System.out.println(CYAN + "║ " + YELLOW + "[9]" + RESET + " Xem Lịch sử tìm kiếm gần đây " + GREEN + "[Queue]" + RESET + "                       " + CYAN + "║");
            System.out.println(CYAN + "║ " + YELLOW + "[10]" + RESET + " Xem Tổng tiền Giỏ hàng " + GREEN + "[Linked List]" + RESET + "                      " + CYAN + "║");
            System.out.println(CYAN + "║ " + YELLOW + "[11]" + RESET + " Thêm sản phẩm vào Giỏ hàng " + GREEN + "[Linked List]" + RESET + "                  " + CYAN + "║");
            System.out.println(CYAN + "║ " + RED + "[0]" + RESET + " Thoát chương trình                                          " + CYAN + "║");
            System.out.println(CYAN + BOLD + "╚═════════════════════════════════════════════════════════════════╝" + RESET);
            System.out.print(BOLD + "Mời bạn chọn chức năng (0-11): " + RESET);
            
            String choice = scanner.nextLine();
            System.out.println();
            
            try {
                switch (choice) {
                    case "1":
                        System.out.println(BLUE + "--- THÊM SẢN PHẨM ---" + RESET);
                        System.out.print("Nhập ID: ");
                        String id1 = scanner.nextLine();
                        if (system.searchEngine.getById(id1) != null) {
                            System.out.println(RED + "Lỗi: ID đã tồn tại!" + RESET);
                            break;
                        }
                        System.out.print("Nhập tên: ");
                        String name1 = scanner.nextLine();
                        System.out.print("Nhập giá: ");
                        double price1 = Double.parseDouble(scanner.nextLine());
                        System.out.print("Nhập đánh giá (1-5): ");
                        double rating1 = Double.parseDouble(scanner.nextLine());
                        system.addProduct(new Product(id1, name1, price1, rating1));
                        System.out.println(GREEN + "✔ Thêm thành công!" + RESET);
                        break;
                    case "2":
                        System.out.println(BLUE + "--- DANH SÁCH TẤT CẢ SẢN PHẨM ---" + RESET);
                        printProductTable(system.getAllProducts());
                        break;
                    case "3":
                        System.out.println(BLUE + "--- CẬP NHẬT SẢN PHẨM ---" + RESET);
                        System.out.print("Nhập ID cần cập nhật: ");
                        String id3 = scanner.nextLine();
                        Product oldP = system.searchEngine.getById(id3);
                        if (oldP == null) {
                            System.out.println(RED + "✖ Không tìm thấy sản phẩm!" + RESET);
                            break;
                        }
                        System.out.print("Nhập tên mới: ");
                        String name3 = scanner.nextLine();
                        System.out.print("Nhập giá mới: ");
                        double price3 = Double.parseDouble(scanner.nextLine());
                        System.out.print("Nhập đánh giá mới (1-5): ");
                        double rating3 = Double.parseDouble(scanner.nextLine());
                        system.updateProduct(id3, name3, price3, rating3);
                        System.out.println(GREEN + "✔ Cập nhật thành công!" + RESET);
                        break;
                    case "4":
                        System.out.println(BLUE + "--- XÓA SẢN PHẨM ---" + RESET);
                        System.out.print("Nhập ID cần xóa: ");
                        String id4 = scanner.nextLine();
                        if (system.deleteProduct(id4)) {
                            System.out.println(GREEN + "✔ Đã xóa thành công!" + RESET);
                        } else {
                            System.out.println(RED + "✖ Không tìm thấy sản phẩm!" + RESET);
                        }
                        break;
                    case "5":
                        System.out.println(BLUE + "--- TÌM KIẾM THEO ID ---" + RESET);
                        System.out.print("Nhập ID: ");
                        String id5 = scanner.nextLine();
                        Product p5 = system.searchEngine.getById(id5);
                        printSingleProduct(p5);
                        break;
                    case "6":
                        System.out.println(BLUE + "--- LỌC THEO KHOẢNG GIÁ ---" + RESET);
                        System.out.print("Nhập giá nhỏ nhất: ");
                        double minPrice = Double.parseDouble(scanner.nextLine());
                        System.out.print("Nhập giá lớn nhất: ");
                        double maxPrice = Double.parseDouble(scanner.nextLine());
                        printProductTable(system.filterByPrice(minPrice, maxPrice));
                        break;
                    case "7":
                        System.out.println(BLUE + "--- TÌM KIẾM TỰ ĐỘNG ---" + RESET);
                        System.out.print("Nhập từ khóa bắt đầu (Prefix): ");
                        String prefix = scanner.nextLine();
                        printProductTable(system.autocomplete(prefix));
                        break;
                    case "8":
                        System.out.println(BLUE + "--- LỌC THEO RATING ---" + RESET);
                        System.out.print("Nhập mức Đánh giá cụ thể (Ví dụ: 5.0, 4.0): ");
                        double ratingMatch = Double.parseDouble(scanner.nextLine());
                        printProductTable(system.getProductsByRating(ratingMatch));
                        break;
                    case "9":
                        System.out.println(BLUE + "--- LỊCH SỬ TÌM KIẾM ---" + RESET);
                        String[] history = system.searchHistory.getRecentSearches();
                        boolean hasHistory = false;
                        for (int i = 0; i < history.length; i++) {
                            if (history[i] != null) {
                                System.out.println(YELLOW + " 🕒 " + history[i] + RESET);
                                hasHistory = true;
                            }
                        }
                        if (!hasHistory) System.out.println("Chưa có lịch sử tìm kiếm nào.");
                        break;
                    case "10":
                        System.out.println(BLUE + "--- THÔNG TIN GIỎ HÀNG ---" + RESET);
                        System.out.println(GREEN + "🛒 Tổng tiền phải thanh toán: " + BOLD + system.shoppingCart.calculateTotal() + " $" + RESET);
                        break;
                    case "11":
                        System.out.println(BLUE + "--- THÊM VÀO GIỎ HÀNG ---" + RESET);
                        System.out.print("Nhập ID sản phẩm để thêm vào giỏ: ");
                        String id11 = scanner.nextLine();
                        Product p11 = system.searchEngine.getById(id11);
                        if (p11 == null) {
                            System.out.println(RED + "✖ Sản phẩm không tồn tại!" + RESET);
                            break;
                        }
                        System.out.print("Nhập số lượng: ");
                        int qty = Integer.parseInt(scanner.nextLine());
                        system.shoppingCart.addProduct(p11, qty);
                        System.out.println(GREEN + "✔ Đã thêm " + qty + " sản phẩm vào giỏ hàng thành công!" + RESET);
                        break;
                    case "0":
                        System.out.println(PURPLE + BOLD + "Cảm ơn bạn đã sử dụng hệ thống! Tạm biệt." + RESET);
                        scanner.close();
                        return;
                    default:
                        System.out.println(RED + "✖ Lựa chọn không hợp lệ! Vui lòng chọn từ 0-11." + RESET);
                }
            } catch (Exception e) {
                System.out.println(RED + "✖ Lỗi nhập liệu! Vui lòng thử lại." + RESET);
            }
            System.out.println(YELLOW + "\nNhấn Enter để tiếp tục..." + RESET);
            scanner.nextLine();
        }
    }

    private static void printProductTable(datastructure.array.ProductArray products) {
        if (products.size == 0) {
            System.out.println(RED + "Không có sản phẩm nào để hiển thị." + RESET);
            return;
        }
        System.out.println(CYAN + "┌────────┬──────────────────────────────────────────┬────────────┬──────────┐" + RESET);
        System.out.printf(CYAN + "│" + BOLD + " %-6s " + CYAN + "│" + BOLD + " %-40s " + CYAN + "│" + BOLD + " %-10s " + CYAN + "│" + BOLD + " %-8s " + CYAN + "│\n" + RESET, "ID", "Tên Sản Phẩm", "Giá ($)", "Đánh giá");
        System.out.println(CYAN + "├────────┼──────────────────────────────────────────┼────────────┼──────────┤" + RESET);
        for (int i = 0; i < products.size; i++) {
            Product p = products.get(i);
            if (p == null) continue;
            String name = p.getName();
            if (name.length() > 40) {
                name = name.substring(0, 37) + "...";
            }
            System.out.printf(CYAN + "│" + RESET + " %-6s " + CYAN + "│" + RESET + " %-40s " + CYAN + "│" + YELLOW + " %-10.2f " + CYAN + "│" + GREEN + " %-8.1f " + CYAN + "│\n" + RESET, p.getId(), name, p.getPrice(), p.getRating());
        }
        System.out.println(CYAN + "└────────┴──────────────────────────────────────────┴────────────┴──────────┘" + RESET);
    }

    private static void printSingleProduct(Product p) {
        if (p == null) {
            System.out.println(RED + "✖ Không tìm thấy sản phẩm!" + RESET);
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
            System.out.println(RED + "Lỗi đọc file hoặc file không tồn tại. Đang chạy bằng dữ liệu trống." + RESET);
        }
    }
}
