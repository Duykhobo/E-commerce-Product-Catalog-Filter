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
        // 1. Tự tính mã hash bằng thuật toán Polynomial Rolling Hash (giống
        // String.hashCode của Java)
        int hashCode = 0;
        for (int i = 0; i < key.length(); i++) {
            hashCode = 31 * hashCode + key.charAt(i);
        }
        // 2. Tự ép về số dương (không dùng Math.abs)
        int absHashCode = hashCode;
        if (absHashCode < 0) {
            if (absHashCode == Integer.MIN_VALUE) {
                absHashCode = Integer.MAX_VALUE; // Tránh tràn số khi đổi dấu của số âm nhỏ nhất
            } else {
                absHashCode = -absHashCode;
            }
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
