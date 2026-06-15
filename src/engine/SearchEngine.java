package engine;

import datastructure.hash.HashNode;
import entity.Product;

public class SearchEngine {
    public HashNode<String, Product>[] hashTable;
    public int capacity;

    public SearchEngine(int tableCapacity) {
        this.capacity = tableCapacity;
        @SuppressWarnings("unchecked")
        HashNode<String, Product>[] newTable = (HashNode<String, Product>[]) new HashNode[tableCapacity];
        this.hashTable = newTable;
    }

    // TODO (Nguyễn Ngọc Minh Tân): Viết hàm tính Hash Index từ String key
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
        // ...
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
}
