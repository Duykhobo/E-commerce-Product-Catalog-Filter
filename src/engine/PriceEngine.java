package engine;

import datastructure.array.ProductArray;
import datastructure.tree.PriceNode;
import entity.Product;

// Lớp này triển khai cấu trúc dữ liệu Binary Search Tree - BST (Cây nhị phân tìm kiếm)
// Mục đích: Phân loại và lưu trữ sản phẩm theo giá để thực hiện Lọc khoảng giá siêu tốc
public class PriceEngine {
    public PriceNode root; // Node gốc (đỉnh) của cây
    private int size;      // Biến đếm tổng số lượng sản phẩm đang có trên cây

    public PriceEngine() {
        this.root = null; // Ban đầu cây rỗng
        this.size = 0;
    }

    // Hàm kiểm tra cây rỗng
    public boolean isEmpty() {
        return size == 0;
    }

    // Triển khai thuật toán chèn Product vào cây BST kết hợp Danh sách
    // Độ phức tạp trung bình: O(log N)
    public void insertProduct(Product p) {
        if (p == null) return; // Không chèn đối tượng rỗng
        
        // Trường hợp 1: Nếu cây rỗng, tạo Node Gốc mới
        if (isEmpty()) {
            root = new PriceNode(p.getPrice()); // Mức giá của sản phẩm chính là Khóa (Key) của Node
            root.addProduct(p); // Thêm sản phẩm vào mảng động bên trong Node đó
            size++;
            return;
        }

        // Trường hợp 2: Nếu cây đã có phần tử, thực hiện duyệt tìm vị trí thích hợp để chèn
        PriceNode current = root; // Biến chạy bắt đầu từ gốc
        PriceNode parent = null;  // Biến lưu trữ Node cha của current
        
        while (current != null) {
            parent = current; // Ghi nhớ lại Node cha trước khi current di chuyển xuống dưới
            
            // So sánh giá của sản phẩm mới với giá của Node hiện tại
            if (p.getPrice() == current.getPrice()) {
                // Đụng độ giá: Đã có Node chứa mức giá này trên cây!
                // Giải quyết: KHÔNG tạo Node mới, chỉ cần lấy sản phẩm nhét thêm vào mảng của Node hiện tại (O(1))
                // Giúp cây không bị mất cân bằng và không bị trùng lặp Node
                current.addProduct(p);
                size++;
                return; // Xong, kết thúc
            } else if (p.getPrice() < current.getPrice()) {
                // Giá nhỏ hơn -> Đi sang nhánh Trái
                current = current.getLeft();
            } else {
                // Giá lớn hơn -> Đi sang nhánh Phải
                current = current.getRight();
            }
        }

        // Đã đến được vị trí lá (current == null), tiến hành tạo Node mới
        PriceNode newNode = new PriceNode(p.getPrice()); // Khởi tạo Node với mức giá mới
        newNode.addProduct(p); // Bỏ sản phẩm vào mảng của Node mới
        
        // Liên kết Node mới với Node cha (parent)
        if (p.getPrice() < parent.getPrice()) {
            parent.setLeft(newNode);  // Nối vào bên trái
        } else {
            parent.setRight(newNode); // Nối vào bên phải
        }
        size++; // Tăng tổng số lượng sản phẩm lên 1
    }

    // Tìm kiếm khoảng giá (Min - Max) bằng thuật toán In-Order Traversal (Duyệt Trung Thứ Tự: Trái - Gốc - Phải)
    // Ưu điểm In-Order: Kết quả trả về sẽ tự động được SẮP XẾP TĂNG DẦN theo giá
    public void searchByPriceRange(PriceNode node, double min, double max, ProductArray result) {
        if (node == null) {
            return; // Trạng thái dừng của Đệ quy
        }

        double currentPrice = node.getPrice(); // Lấy giá của Node đang xét

        // BƯỚC 1: DUYỆT NHÁNH TRÁI
        // Chỉ đi sang nhánh trái nếu mức giá hiện tại CÒN LỚN HƠN min.
        // (Nếu giá hiện tại đã nhỏ hơn min rồi thì toàn bộ nhánh trái chắc chắn cũng nhỏ hơn min, không cần vào tìm cho tốn thời gian - Kỹ thuật Cắt tỉa (Pruning))
        if (currentPrice > min) {
            searchByPriceRange(node.getLeft(), min, max, result);
        }

        // BƯỚC 2: XỬ LÝ NODE GỐC (NODE HIỆN TẠI)
        // Kiểm tra xem giá của Node hiện tại có nằm VỪA VẶN trong khoảng [min, max] hay không
        if (currentPrice >= min && currentPrice <= max) {
            ProductArray products = node.getProducts(); // Lấy mảng sản phẩm chứa trong Node
            
            // Đổ toàn bộ sản phẩm hợp lệ vào mảng result (đầu ra)
            for (int i = 0; i < products.size; i++) {
                Product p = products.get(i);
                if (p != null && p.isActive()) {
                    result.add(p);
                }
            }
        }

        // BƯỚC 3: DUYỆT NHÁNH PHẢI
        // Chỉ đi sang nhánh phải nếu mức giá hiện tại VẪN NHỎ HƠN HOẶC BẰNG max.
        // (Nếu giá hiện tại đã lớn hơn max rồi thì toàn bộ nhánh phải chắc chắn sẽ càng lớn hơn max, không cần tìm nữa)
        if (currentPrice <= max) {
            searchByPriceRange(node.getRight(), min, max, result);
        }
    }
}
