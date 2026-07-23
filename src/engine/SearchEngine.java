package engine;

import datastructure.array.ProductArray;
import datastructure.hash.HashNode;
import entity.Product;

// Lớp này xử lý việc tìm kiếm sản phẩm theo ID (dùng Hash Table) 
// và tìm kiếm tự động Autocomplete (dùng mảng động)
public class SearchEngine {
    public HashNode[] hashTable;       // Mảng chứa các Node của Bảng Băm
    public int capacity;               // Sức chứa của mảng (ví dụ: 100)
    public ProductArray productArray;  // Mảng động lưu trữ toàn bộ sản phẩm (thay cho cấu trúc Trie phức tạp)

    public SearchEngine(int tableCapacity) {
        this.capacity = tableCapacity;
        // Khởi tạo mảng Hash Table với kích thước cho trước
        HashNode[] newTable = new HashNode[tableCapacity];
        this.hashTable = newTable;
        this.productArray = new ProductArray();
    }

    // TODO (Nguyễn Ngọc Minh Tân): Viết hàm tính Hash Index từ String key
    // Hàm này sẽ băm (hash) một chuỗi (ví dụ: ID) thành một con số nguyên, 
    // sau đó chia lấy dư cho capacity để ra được vị trí (index) trong mảng
    public int getBucketIndex(String key) {
        if (key == null) {
            return 0;
        }
        int hashCode = 0;
        // Thuật toán Horner băm chuỗi thành số
        for (int i = 0; i < key.length(); i++) {
            hashCode = 31 * hashCode + key.charAt(i);
        }
        // Đảm bảo index luôn dương
        int absHashCode = Math.abs(hashCode);
        if (absHashCode < 0) {
            absHashCode = Integer.MAX_VALUE;
        }
        // Trả về vị trí index trong giới hạn mảng [0, capacity - 1]
        return absHashCode % capacity;
    }

    // TODO (Phan Khánh Duy): Viết thuật toán chèn Product vào hashTable và xử lý đụng độ (Chaining)
    public void put(String key, Product product) {
        if (key == null || product == null) {
            return;
        }
        // Bước 1: Tính toán index từ khóa
        int index = getBucketIndex(key);
        
        // Bước 2: Nếu tại vị trí index chưa có gì, tạo Node mới đưa vào luôn
        if (hashTable[index] == null) {
            hashTable[index] = new HashNode(key, product);
        } else {
            // Bước 3: Nếu tại vị trí index ĐÃ CÓ Node (Đụng độ - Collision)
            // Duyệt danh sách liên kết tại vị trí đó (Phương pháp Chaining)
            HashNode current = hashTable[index]; // Bắt đầu từ Node đầu tiên trong danh sách liên kết
            
            while (current != null) {
                // Trường hợp ID đã tồn tại -> Cập nhật lại giá trị (Value) mới
                if (current.getKey().equals(key)) {
                    current.setValue(product);
                    return;
                }
                // Nếu đã đi đến cuối danh sách liên kết mà vẫn chưa thấy trùng ID
                // -> Tạo Node mới và nối vào cuối (Tail)
                if (current.getNext() == null) {
                    current.setNext(new HashNode(key, product));
                    return;
                }
                // Di chuyển sang Node tiếp theo
                current = current.getNext();
            }
        }
    }

    // TODO (Nguyễn Ngọc Minh Tân): Triển khai thuật toán chèn tên sản phẩm vào mảng động
    public void insertToArray(String name, Product product) {
        if (product == null) {
            return;
        }
        // Thêm thẳng vào mảng động ProductArray (O(1) trong đa số trường hợp)
        productArray.add(product);
    }

    // Hàm phục vụ chức năng tìm kiếm Autocomplete (Duyệt mảng O(N))
    public ProductArray searchByPrefix(String prefix) {
        ProductArray result = new ProductArray(); // Mảng kết quả
        if (prefix == null || prefix.isEmpty()) {
            return result;
        }
        prefix = prefix.toLowerCase(); // Đưa về chữ thường để so sánh không phân biệt hoa/thường
        
        // Duyệt toàn bộ mảng sản phẩm
        for (int i = 0; i < productArray.size; i++) {
            Product p = productArray.get(i); // Lấy sản phẩm tại vị trí i
            if (p == null || !p.isActive())
                continue; // Bỏ qua sản phẩm rỗng hoặc đã bị xóa (Soft delete)
                
            // Nếu tên sản phẩm bắt đầu bằng từ khóa prefix (Ví dụ: "Lap" -> "Laptop")
            if (p.getName().toLowerCase().startsWith(prefix)) {
                result.add(p); // Thêm vào mảng kết quả
            }
        }
        return result;
    }

    // Hàm lấy thông tin sản phẩm theo ID bằng Hash Table (Độ phức tạp O(1) trung bình)
    public Product getById(String id) {
        // Tính toán index
        int index = getBucketIndex(id);
        
        // Lấy Node tại vị trí index
        HashNode current = hashTable[index];
        
        // Duyệt danh sách liên kết tại vị trí đó để tìm chính xác ID
        while (current != null) {
            if (current.getKey().equals(id)) {
                Product p = (Product) current.getValue(); // Ép kiểu từ Object về Product
                // Chỉ trả về nếu sản phẩm chưa bị xóa
                if (p.isActive()) {
                    return p;
                }
            }
            // Không khớp ID thì đi tiếp sang Node kế (do đụng độ)
            current = current.getNext();
        }
        return null; // Không tìm thấy
    }
}
