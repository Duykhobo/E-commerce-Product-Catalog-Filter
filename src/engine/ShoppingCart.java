package engine;

import entity.Product;

// Lớp này triển khai cấu trúc dữ liệu Singly Linked List (Danh sách liên kết đơn)
// Mục đích: Dùng để lưu trữ Giỏ hàng (số lượng mua tùy ý, danh sách linh hoạt)
public class ShoppingCart {

    // Lớp Node nội bộ dành riêng cho Giỏ hàng
    // Mỗi Node (CartNode) đại diện cho một mặt hàng nằm trong giỏ
    private static class CartNode {
        Product product;    // Dữ liệu 1: Sản phẩm (Tên, Giá, ID...)
        int quantity;       // Dữ liệu 2: Số lượng mua của sản phẩm đó
        CartNode next;      // Con trỏ (Pointer): Trỏ đến mặt hàng tiếp theo trong giỏ

        // Hàm khởi tạo Node
        CartNode(Product product, int quantity) {
            this.product = product;
            this.quantity = quantity;
            this.next = null; // Mặc định khi mới tạo, Node chưa nối vào đâu nên next = null
        }
    }

    private CartNode head;    // Con trỏ quản lý toàn bộ List, trỏ vào phần tử ĐẦU TIÊN của giỏ hàng
    private int totalItems;   // Biến phụ lưu trữ tổng số loại mặt hàng đang có trong giỏ

    public ShoppingCart() {
        this.head = null;     // Ban đầu giỏ hàng trống nên head = null
        this.totalItems = 0;
    }

    // Thêm sản phẩm vào giỏ (Thao tác Insert)
    public void addProduct(Product p, int quantity) {
        // Kiểm tra dữ liệu đầu vào
        if (p == null || quantity <= 0) return;

        // BƯỚC 1: KIỂM TRA SẢN PHẨM ĐÃ CÓ TRONG GIỎ CHƯA
        CartNode current = head; // Bắt đầu duyệt từ đầu danh sách (head)
        while (current != null) { // Lặp cho đến khi hết danh sách
            if (current.product.getId().equals(p.getId())) {
                // Nếu tìm thấy ID trùng khớp -> Sản phẩm đã có trong giỏ
                current.quantity += quantity; // Chỉ cần cộng dồn thêm số lượng mua
                return; // Kết thúc hàm
            }
            current = current.next; // Di chuyển sang phần tử tiếp theo
        }

        // BƯỚC 2: NẾU SẢN PHẨM CHƯA CÓ TRONG GIỎ
        // Tạo một Node mới chứa thông tin sản phẩm và số lượng
        CartNode newNode = new CartNode(p, quantity);
        
        // Chèn Node mới vào ĐẦU danh sách (Insert at Head) - Độ phức tạp O(1)
        newNode.next = head; // Cho con trỏ next của Node mới trỏ vào Node đầu tiên cũ
        head = newNode;      // Cập nhật lại head để Node mới trở thành phần tử ĐẦU TIÊN
        
        totalItems++; // Tăng tổng số loại mặt hàng lên 1
    }

    // Xóa sản phẩm khỏi giỏ theo ID (Thao tác Delete)
    public void removeProduct(String productId) {
        // Nếu danh sách trống thì không làm gì cả
        if (head == null) return;

        // TRƯỜNG HỢP 1: Phần tử cần xóa nằm ngay ĐẦU danh sách (head)
        if (head.product.getId().equals(productId)) {
            head = head.next; // Trỏ head sang phần tử thứ 2, phần tử đầu cũ sẽ bị Java Garbage Collector thu hồi
            totalItems--;
            return;
        }

        // TRƯỜNG HỢP 2: Phần tử cần xóa nằm ở GIỮA hoặc CUỐI danh sách
        CartNode current = head;
        // Duyệt tìm phần tử NGAY TRƯỚC phần tử cần xóa (vì là danh sách đơn, cần giữ được Node đứng trước)
        while (current.next != null) {
            if (current.next.product.getId().equals(productId)) {
                // Đã tìm thấy. Thực hiện nối tắt (Bỏ qua Node cần xóa)
                // Con trỏ next của Node hiện tại sẽ trỏ thẳng tới Node đứng sau Node cần xóa
                current.next = current.next.next;
                totalItems--;
                return;
            }
            current = current.next; // Tiếp tục di chuyển
        }
    }

    // Tính tổng tiền giỏ hàng (Thao tác Traversal/Duyệt toàn bộ danh sách)
    public double calculateTotal() {
        double total = 0; // Biến lưu tổng tiền
        CartNode current = head; // Bắt đầu duyệt từ đầu danh sách
        
        // Vòng lặp duyệt qua từng Node cho đến cuối danh sách (current == null)
        while (current != null) {
            // Cộng dồn: Giá * Số lượng
            total += current.product.getPrice() * current.quantity;
            current = current.next; // Nhảy sang Node tiếp theo
        }
        return total; // Trả về tổng tiền
    }

    // Xóa toàn bộ giỏ hàng
    public void clearCart() {
        head = null;     // Ngắt kết nối toàn bộ danh sách, GC sẽ tự thu hồi
        totalItems = 0;  // Trả số lượng về 0
    }
}
