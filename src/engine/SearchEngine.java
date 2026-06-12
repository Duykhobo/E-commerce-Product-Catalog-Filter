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
        // Gợi ý: dùng Math.abs(key.hashCode()) % capacity
        return 0;
    }

    // TODO (Phan Khánh Duy): Viết thuật toán chèn Product vào hashTable và xử lý đụng độ (Chaining)
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
