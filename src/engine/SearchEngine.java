package engine;

import datastructure.hash.HashNode;
import datastructure.trie.TrieNode;
import entity.Product;
import datastructure.list.ProductLinkedList;

public class SearchEngine {
    public HashNode<String, Product>[] hashTable;
    public int capacity;
    
    // Gốc của cây Trie
    public TrieNode trieRoot;

    public SearchEngine(int tableCapacity) {
        this.capacity = tableCapacity;
        @SuppressWarnings("unchecked")
        HashNode<String, Product>[] newTable = (HashNode<String, Product>[]) new HashNode[tableCapacity];
        this.hashTable = newTable;
        this.trieRoot = new TrieNode();
    }

    // Đã hoàn thành: Hàm tính Hash Index từ String key
    public int getBucketIndex(String key) {
        if (key == null) {
            return 0;
        }
        // 1. Tự tính mã hash bằng thuật toán Polynomial Rolling Hash
        int hashCode = 0;
        for (int i = 0; i < key.length(); i++) {
            hashCode = 31 * hashCode + key.charAt(i);
        }

        // 2. Ép về số dương sử dụng Math.abs
        int absHashCode = Math.abs(hashCode);
        if (absHashCode < 0) {
            // Xử lý trường hợp đặc biệt Integer.MIN_VALUE (vì Math.abs của số này vẫn ra số
            // âm)
            absHashCode = Integer.MAX_VALUE;
        }

        // 3. Lấy phần dư trong giới hạn mảng (Index)
        return absHashCode % capacity;
    }

    // TODO (Phan Khánh Duy): Viết thuật toán chèn Product vào hashTable và xử lý
    // đụng độ (Chaining)
    public void put(String key, Product product) {
        int index = getBucketIndex(key);
        // kiểm tra toàn bộ mảng, nếu trống => tạo node mới
        if (hashTable[index] == null) {
            hashTable[index] = new HashNode<>(key, product);
        } else {
            HashNode<String, Product> current = hashTable[index];
            // nếu xảy ra va chạm, dùng một vòng lặp để duyệt qua các node trong bucket đó
            while (current != null) {
                // trong khi duyệt nếu tìm thấy node nào key trùng với key cần chèn thì ghi đè
                // sản phẩm
                if (current.key.equals(key)) {
                    current.value = product; // Cập nhật giá trị nếu key đã tồn tại
                    return;
                }
                // nếu duyệt đến node cuối mà không thấy key trùng với ID thì tạo hashNode mới
                // và thêm vào cuối danh sách liên kết
                if (current.next == null) {
                    current.next = new HashNode<>(key, product);
                    return;
                }
                current = current.next;
            }
        }
    }

    // Snippet đã cung cấp trong báo cáo
    public Product getById(String id) {
        int index = getBucketIndex(id);

        HashNode<String, Product> current = hashTable[index];
        while (current != null) {
            if (current.key.equals(id)) {
                return current.value;
            }
            current = current.next;
        }
        return null;
    }

    // TODO (Nguyễn Ngọc Minh Tân): Triển khai thuật toán chèn tên sản phẩm vào cây Trie
public void insertToTrie(String name, Product product) {
    name = name.toLowerCase();
        if (name == null || name.isEmpty()) {
            return;
        }
        
        TrieNode current = this.trieRoot;
        
        // 1. Duyệt qua từng ký tự của chuỗi name
        for (int i = 0; i < name.length(); i++) {
            char c = name.charAt(i);
            
            // Chuyển ký tự thành chỉ số mảng (0-255)
            int index = (int) c;
            
            // Đảm bảo chỉ số nằm trong khoảng hợp lệ từ 0 đến 255 (ASCII) để tránh lỗi tràn mảng
            if (index < 0 || index >= 256) {
                continue; 
            }
            
            // 2. Nếu children[index] == null thì khởi tạo TrieNode mới
            if (current.children[index] == null) {
                current.children[index] = new TrieNode();
            }
            
            // Di chuyển node hiện tại xuống node con tiếp theo
            current = current.children[index];
        }
        
        // 3. Cuối từ thì đánh dấu isEndOfWord = true và thêm product vào danh sách kết quả
        current.isEndOfWord = true;
        current.products.add(product);
    }

    // TODO (Phan Khánh Duy): Triển khai thuật toán duyệt cây Trie để lấy các sản phẩm khớp với tiền tố (Prefix Search)
    public ProductLinkedList searchByPrefix(String prefix) {
        ProductLinkedList result = new ProductLinkedList();
        // Gợi ý: Lội qua cây Trie theo chuỗi prefix. Nếu đứt gánh giữa đường -> return rỗng.
        // Nếu đến được điểm kết thúc của prefix, gọi hàm đệ quy hoặc vòng lặp gom tất cả sản phẩm
        // nằm trong toàn bộ các nhánh con bên dưới node hiện tại vào biến result.
        return result;
    }
}
