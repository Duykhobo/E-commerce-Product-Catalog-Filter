import java.util.List;
import java.util.Scanner;

import core.CatalogFilterSystem;
import datastructure.array.ProductArray;
import entity.Product;
import utils.FileUtils;

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
    
    static Scanner scanner = new Scanner(System.in);

    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public static void main(String[] args) {
        CatalogFilterSystem system = new CatalogFilterSystem(100, 100);
        
        clearScreen();
        System.out.println(CYAN + BOLD + "========================================================" + RESET);
        System.out.println(CYAN + BOLD + "|        DANG KHOI DONG HE THONG E-COMMERCE...         |" + RESET);
        System.out.println(CYAN + BOLD + "========================================================" + RESET);

        System.out.println(YELLOW + "=> Dang doc du lieu tu file data.csv..." + RESET);
        loadDataFromFile(system, "data.csv");
        System.out.println(GREEN + "=> Nap du lieu hoan tat! Nhan Enter de tiep tuc..." + RESET);
        scanner.nextLine();

        while (true) {
            clearScreen();
            System.out.println(PURPLE + BOLD
                    + "    ███████╗ ██████╗ ██████╗ ███╗   ███╗███╗   ███╗███████╗██████╗  ██████╗███████╗");
            System.out.println("    ██╔════╝██╔════╝██╔═══██╗████╗ ████║████╗ ████║██╔════╝██╔══██╗██╔════╝██╔════╝");
            System.out.println("    █████╗  ██║     ██║   ██║██╔████╔██║██╔████╔██║█████╗  ██████╔╝██║     █████╗  ");
            System.out.println("    ██╔══╝  ██║     ██║   ██║██║╚██╔╝██║██║╚██╔╝██║██╔══╝  ██╔══██╗██║     ██╔══╝  ");
            System.out.println("    ███████╗╚██████╗╚██████╔╝██║ ╚═╝ ██║██║ ╚═╝ ██║███████╗██║  ██║╚██████╗███████╗");
            System.out.println(
                    "    ╚══════╝ ╚═════╝ ╚═════╝ ╚═╝     ╚═╝╚═╝     ╚═╝╚══════╝╚═╝  ╚═╝ ╚═════╝╚══════╝" + RESET);

            System.out.println(
                    "\n" + CYAN + BOLD + "======================== QUAN LY DANH MUC ========================" + RESET);
            System.out.println(CYAN + "| " + YELLOW + "[1]" + RESET
                    + " Them san pham moi (Create)                                  " + CYAN + "|");
            System.out.println(CYAN + "| " + YELLOW + "[2]" + RESET
                    + " Hien thi tat ca san pham (Read)                             " + CYAN + "|");
            System.out.println(CYAN + "| " + YELLOW + "[3]" + RESET
                    + " Cap nhat thong tin san pham (Update)                        " + CYAN + "|");
            System.out.println(CYAN + "| " + YELLOW + "[4]" + RESET
                    + " Xoa san pham (Delete)                                       " + CYAN + "|");
            System.out.println(CYAN + "| " + YELLOW + "[5]" + RESET + " Tim kiem san pham theo ID " + GREEN
                    + "[Hash Table]" + RESET + "                      " + CYAN + "|");
            System.out.println(CYAN + "| " + YELLOW + "[6]" + RESET + " Loc san pham theo khoang gia " + GREEN + "[BST]"
                    + RESET + "                          " + CYAN + "|");
            System.out.println(CYAN + "| " + YELLOW + "[7]" + RESET + " Tim kiem tu dong (Autocomplete) " + GREEN
                    + "[Array]" + RESET + "                     " + CYAN + "|");
            System.out.println(CYAN + "| " + YELLOW + "[9]" + RESET + " Xem Lich su tim kiem gan day " + GREEN
                    + "[Queue]" + RESET + "                       " + CYAN + "|");
            System.out.println(CYAN + "| " + YELLOW + "[10]" + RESET + " Xem Tong tien Gio hang " + GREEN
                    + "[Linked List]" + RESET + "                      " + CYAN + "|");
            System.out.println(CYAN + "| " + YELLOW + "[11]" + RESET + " Them san pham vao Gio hang " + GREEN
                    + "[Linked List]" + RESET + "                  " + CYAN + "|");
            System.out.println(CYAN + "| " + YELLOW + "[12]" + RESET
                    + " Khoi phuc san pham da xoa (Restore)                        " + CYAN + "|");
            System.out.println(CYAN + "| " + RED + "[0]" + RESET
                    + " Thoat chuong trinh                                         " + CYAN + "|");
            System.out.println(
                    CYAN + BOLD + "==================================================================" + RESET);
            System.out.print(BOLD + "=> Moi ban chon chuc nang (0-12): " + RESET);

            String choice = scanner.nextLine();
            System.out.println();

            try {
                switch (choice) {
                    case "1":
                        System.out.println(BLUE + "--- THEM SAN PHAM ---" + RESET);
                        System.out.print("Nhap ID: ");
                        String id1 = scanner.nextLine().trim().toUpperCase();
                        if (system.searchEngine.getById(id1) != null) {
                            System.out.println(RED + "Loi: ID da ton tai!" + RESET);
                            break;
                        }
                        System.out.print("Nhap ten: ");
                        String name1 = scanner.nextLine();
                        System.out.print("Nhap gia: ");
                        double price1 = Double.parseDouble(scanner.nextLine());
                        System.out.print("Nhap danh gia (1-5): ");
                        double rating1 = Double.parseDouble(scanner.nextLine());
                        system.addProduct(new Product(id1, name1, price1, rating1));
                        saveDataToFile(system, "data.csv");
                        System.out.println(GREEN + "Them thanh cong!" + RESET);
                        break;
                    case "2":
                        System.out.println(BLUE + "--- DANH SACH TAT CA SAN PHAM ---" + RESET);
                        printProductTable(system.getAllProducts());
                        break;
                    case "3":
                        System.out.println(BLUE + "--- CAP NHAT SAN PHAM ---" + RESET);
                        printProductTable(system.getAllProducts());
                        System.out.print("Nhap ID can cap nhat: ");
                        String id3 = scanner.nextLine().trim().toUpperCase();
                        Product oldP = system.searchEngine.getById(id3);
                        if (oldP == null) {
                            System.out.println(RED + "Khong tim thay san pham!" + RESET);
                            break;
                        }
                        System.out.print("Nhap ten moi [" + oldP.getName() + "]: ");
                        String name3 = scanner.nextLine();
                        if (name3.trim().isEmpty())
                            name3 = oldP.getName();

                        System.out.print("Nhap gia moi [" + oldP.getPrice() + "]: ");
                        String priceStr = scanner.nextLine();
                        double price3 = oldP.getPrice();
                        if (!priceStr.trim().isEmpty())
                            price3 = Double.parseDouble(priceStr);

                        System.out.print("Nhap danh gia moi (1-5) [" + oldP.getRating() + "]: ");
                        String ratingStr = scanner.nextLine();
                        double rating3 = oldP.getRating();
                        if (!ratingStr.trim().isEmpty())
                            rating3 = Double.parseDouble(ratingStr);
                        system.updateProduct(id3, name3, price3, rating3);
                        saveDataToFile(system, "data.csv");
                        System.out.println(GREEN + "Cap nhat thanh cong!" + RESET);
                        break;
                    case "4":
                        System.out.println(BLUE + "--- XOA SAN PHAM ---" + RESET);
                        printProductTable(system.getAllProducts());
                        System.out.print("Nhap ID can xoa: ");
                        String id4 = scanner.nextLine().trim().toUpperCase();
                        if (system.deleteProduct(id4)) {
                            saveDataToFile(system, "data.csv");
                            System.out.println(GREEN + "Da xoa thanh cong!" + RESET);
                        } else {
                            System.out.println(RED + "Khong tim thay san pham!" + RESET);
                        }
                        break;
                    case "5":
                        System.out.println(BLUE + "--- TIM KIEM THEO ID ---" + RESET);
                        System.out.print("Nhap ID: ");
                        String id5 = scanner.nextLine().trim().toUpperCase();
                        Product p5 = system.searchEngine.getById(id5);
                        printSingleProduct(p5);
                        break;
                    case "6":
                        System.out.println(BLUE + "--- LOC THEO KHOANG GIA ---" + RESET);
                        System.out.print("Nhap gia nho nhat: ");
                        String minStr = scanner.nextLine().trim();
                        if (minStr.isEmpty()) {
                            System.out.println(RED + "Khong duoc de trong!" + RESET);
                            break;
                        }
                        double minPrice = Double.parseDouble(minStr);
                        if (minPrice < 0) {
                            System.out.println(RED + "Gia khong duoc am!" + RESET);
                            break;
                        }
                        
                        System.out.print("Nhap gia lon nhat: ");
                        String maxStr = scanner.nextLine().trim();
                        if (maxStr.isEmpty()) {
                            System.out.println(RED + "Khong duoc de trong!" + RESET);
                            break;
                        }
                        double maxPrice = Double.parseDouble(maxStr);
                        if (maxPrice < minPrice) {
                            System.out.println(RED + "Gia lon nhat phai lon hon hoac bang gia nho nhat!" + RESET);
                            break;
                        }
                        printProductTable(system.filterByPrice(minPrice, maxPrice));
                        break;
                    case "7":
                        System.out.println(BLUE + "--- TIM KIEM TU DONG ---" + RESET);
                        System.out.print("Nhap tu khoa bat dau (Prefix): ");
                        String prefix = scanner.nextLine();
                        printProductTable(system.autocomplete(prefix));
                        break;
                    case "9":
                        System.out.println(BLUE + "--- LICH SU TIM KIEM ---" + RESET);
                        String[] history = system.searchHistory.getRecentSearches();
                        boolean hasHistory = false;
                        for (int i = 0; i < history.length; i++) {
                            if (history[i] != null) {
                                System.out.println(YELLOW + " - " + history[i] + RESET);
                                hasHistory = true;
                            }
                        }
                        if (!hasHistory)
                            System.out.println("Chua co lich su tim kiem nao.");
                        break;
                    case "10":
                        System.out.println(BLUE + "--- THONG TIN GIO HANG ---" + RESET);
                        System.out.println(GREEN + "Tong tien phai thanh toan: " + BOLD
                                + system.shoppingCart.calculateTotal() + " $" + RESET);
                        break;
                    case "11":
                        System.out.println(BLUE + "--- THEM VAO GIO HANG ---" + RESET);
                        printProductTable(system.getAllProducts());
                        System.out.print("Nhap ID san pham de them vao gio: ");
                        String id11 = scanner.nextLine().trim().toUpperCase();
                        Product p11 = system.searchEngine.getById(id11);
                        if (p11 == null) {
                            System.out.println(RED + "San pham khong ton tai!" + RESET);
                            break;
                        }
                        System.out.print("Nhap so luong: ");
                        int qty = Integer.parseInt(scanner.nextLine());
                        system.shoppingCart.addProduct(p11, qty);
                        System.out.println(GREEN + "Da them " + qty + " san pham vao gio hang thanh cong!" + RESET);
                        break;
                    case "12":
                        System.out.println(BLUE + "--- KHOI PHUC SAN PHAM ---" + RESET);
                        printProductTable(system.getDeletedProducts());
                        System.out.print("Nhap ID san pham can khoi phuc: ");
                        String id12 = scanner.nextLine().trim().toUpperCase();
                        if (system.restoreProduct(id12)) {
                            saveDataToFile(system, "data.csv");
                            System.out.println(GREEN + "Khoi phuc thanh cong!" + RESET);
                        } else {
                            System.out.println(RED + "Khong tim thay san pham hoac san pham chua bi xoa!" + RESET);
                        }
                        break;
                    case "0":
                        saveDataToFile(system, "data.csv");
                        System.out.println(PURPLE + BOLD + "Cam on ban da su dung he thong! Tam biet." + RESET);
                        scanner.close();
                        return;
                    default:
                        System.out.println(RED + "Lua chon khong hop le! Vui long chon tu 0-12." + RESET);
                }
            } catch (Exception e) {
                System.out.println(RED + "Loi nhap lieu! Vui long thu lai." + RESET);
            }
            System.out.println(YELLOW + "\nNhan Enter de tiep tuc..." + RESET);
            scanner.nextLine();
        }
    }

    private static void printProductTable(datastructure.array.ProductArray products) {
        if (products.size == 0) {
            System.out.println(RED + "Khong co san pham nao de hien thi." + RESET);
            return;
        }

        int pageSize = 10;
        int totalPages = (int) Math.ceil((double) products.size / pageSize);
        int currentPage = 1;
        
        while (true) {
            System.out.println(CYAN + "=========================================================================" + RESET);
            System.out.printf(CYAN + "|" + BOLD + " %-6s " + CYAN + "|" + BOLD + " %-40s " + CYAN + "|" + BOLD + " %-10s " + CYAN + "|" + BOLD + " %-8s " + CYAN + "|\n" + RESET, "ID", "Ten San Pham", "Gia ($)", "Danh gia");
            System.out.println(CYAN + "=========================================================================" + RESET);
            
            int start = (currentPage - 1) * pageSize;
            int end = Math.min(start + pageSize, products.size);
            
            for (int i = start; i < end; i++) {
                Product p = products.get(i);
                if (p == null) continue;
                String name = p.getName();
                if (name.length() > 40) {
                    name = name.substring(0, 37) + "...";
                }
                System.out.printf(CYAN + "|" + RESET + " %-6s " + CYAN + "|" + RESET + " %-40s " + CYAN + "|" + YELLOW + " %-10.2f " + CYAN + "|" + GREEN + " %-8.1f " + CYAN + "|\n" + RESET, p.getId(), name, p.getPrice(), p.getRating());
            }
            System.out.println(CYAN + "=========================================================================" + RESET);
            System.out.println(YELLOW + "Trang " + currentPage + "/" + totalPages + " (Tong: " + products.size + " san pham)" + RESET);
            
            if (totalPages <= 1) {
                break;
            }
            
            System.out.print(BOLD + "Nhan [N] Next, [P] Previous, hoac [Q] Quit: " + RESET);
            String input = scanner.nextLine().trim().toLowerCase();
            
            if (input.equals("n") && currentPage < totalPages) {
                currentPage++;
            } else if (input.equals("p") && currentPage > 1) {
                currentPage--;
            } else if (input.equals("q") || input.isEmpty()) {
                break;
            } else {
                System.out.println(RED + "Lua chon khong hop le hoac da het trang!" + RESET);
            }
        }
    }

    private static void printSingleProduct(Product p) {
        if (p == null) {
            System.out.println(RED + "Khong tim thay san pham!" + RESET);
            return;
        }
        datastructure.array.ProductArray arr = new datastructure.array.ProductArray();
        arr.add(p);
        printProductTable(arr);
    }

    private static void loadDataFromFile(CatalogFilterSystem system, String filename) {
        List<Product> products = FileUtils.readProductsFromFile(filename);
        if (products.isEmpty()) {
            System.out.println(RED + "Loi doc file hoac file rong. Dang chay bang du lieu trong." + RESET);
        } else {
            for (Product p : products) {
                system.addProduct(p);
            }
        }
    }

    private static void saveDataToFile(CatalogFilterSystem system, String filename) {
        ProductArray arr = system.searchEngine.productArray;
        java.util.List<Product> list = new java.util.ArrayList<>();
        for (int i = 0; i < arr.size; i++) {
            if (arr.get(i) != null) {
                list.add(arr.get(i));
            }
        }
        FileUtils.writeProductsToFile(filename, list);
    }
}
