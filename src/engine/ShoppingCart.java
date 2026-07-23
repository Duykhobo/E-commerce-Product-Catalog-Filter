package engine;

import entity.Product;

// Cấu trúc dữ liệu Singly Linked List quản lý Giỏ hàng
public class ShoppingCart {

    // Node chứa thông tin sản phẩm trong giỏ
    private static class CartNode {
        Product product;
        int quantity;
        CartNode next;

        CartNode(Product product, int quantity) {
            this.product = product;
            this.quantity = quantity;
            this.next = null;
        }
    }

    private CartNode head;    // Trỏ phần tử ĐẦU
    private CartNode tail;    // Trỏ phần tử CUỐI
    private int totalItems;   // Tổng số loại mặt hàng

    public ShoppingCart() {
        this.head = null;
        this.tail = null;
        this.totalItems = 0;
    }

    // Thêm sản phẩm vào giỏ
    public void addProduct(Product p, int quantity) {
        if (p == null || quantity <= 0) return;

        // BƯỚC 1: Kiểm tra sản phẩm đã tồn tại chưa
        CartNode current = head;
        while (current != null) {
            if (current.product.getId().equals(p.getId())) {
                current.quantity += quantity;
                return;
            }
            current = current.next;
        }

        // BƯỚC 2: Thêm mới vào CUỐI danh sách (Insert at Tail)
        CartNode newNode = new CartNode(p, quantity);
        if (head == null) {
            head = newNode;
            tail = newNode;
        } else {
            tail.next = newNode;
            tail = newNode;
        }
        
        totalItems++;
    }

    // Xóa sản phẩm khỏi giỏ theo ID
    public void removeProduct(String productId) {
        if (head == null) return;

        // BƯỚC 1: Xóa ở ĐẦU danh sách
        if (head.product.getId().equals(productId)) {
            head = head.next;
            if (head == null) {
                tail = null;
            }
            totalItems--;
            return;
        }

        // BƯỚC 2: Xóa ở GIỮA hoặc CUỐI
        CartNode current = head;
        while (current.next != null) {
            if (current.next.product.getId().equals(productId)) {
                if (current.next == tail) {
                    tail = current;
                }
                current.next = current.next.next;
                totalItems--;
                return;
            }
            current = current.next;
        }
    }

    // Tính tổng tiền giỏ hàng
    public double calculateTotal() {
        double total = 0;
        CartNode current = head;
        
        while (current != null) {
            total += current.product.getPrice() * current.quantity;
            current = current.next;
        }
        return total;
    }

    // Xóa toàn bộ giỏ hàng
    public void clearCart() {
        head = null;
        tail = null;
        totalItems = 0;
    }
}
