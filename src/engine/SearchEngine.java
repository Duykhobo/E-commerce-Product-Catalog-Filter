package engine;

import datastructure.array.ProductArray;
import datastructure.hash.HashNode;
import entity.Product;

public class SearchEngine {
    public HashNode<String, Product>[] hashTable;
    public int capacity;
    public ProductArray productArray; // Dùng Mảng thay cho Trie

    public SearchEngine(int tableCapacity) {
        this.capacity = tableCapacity;
        @SuppressWarnings("unchecked")
        HashNode<String, Product>[] newTable = (HashNode<String, Product>[]) new HashNode[tableCapacity];
        this.hashTable = newTable;
        this.productArray = new ProductArray();
    }

    // TODO (Nguyễn Ngọc Minh Tân): Viết hàm tính Hash Index từ String key
    public int getBucketIndex(String key) {
        if (key == null) {
            return 0;
        }
        int hashCode = 0;
        for (int i = 0; i < key.length(); i++) {
            hashCode = 31 * hashCode + key.charAt(i);

        }
        int absHashCode = Math.abs(hashCode);
        if (absHashCode < 0) {
            absHashCode = Integer.MAX_VALUE;
        }
        return absHashCode % capacity;
    }

    // TODO (Phan Khánh Duy): Viết thuật toán chèn Product vào hashTable và xử lý
    // đụng độ (Chaining)
    public void put(String key, Product product) {
        if (key == null || product == null) {
            return;
        }
        int index = getBucketIndex(key);
        if (hashTable[index] == null) {
            hashTable[index] = new HashNode<String, Product>(key, product);

        } else {
            HashNode<String, Product> current = new HashNode<String, Product>(key, product);
            while (current != null) {
                if (current.key.equals(key)) {
                    current.value = product;
                    return;
                }
                if (current.next == null) {
                    current.next = new HashNode<String, Product>(key, product);
                    return;
                }
                current = current.next;
            }
        }
    }

    // TODO (Nguyễn Ngọc Minh Tân): Triển khai thuật toán chèn tên sản phẩm vào mảng
    // động
    public void insertToArray(String name, Product product) {
        if (product == null) {
            return;
        }
        productArray.add(product);
    }

    public ProductArray searchByPrefix(String prefix) {
        ProductArray result = new ProductArray();
        if (prefix == null || prefix.isEmpty()) {
            return result;
        }
        prefix = prefix.toLowerCase();
        for (int i = 0; i < productArray.size; i++) {
            Product p = productArray.data[i];
            if (!p.isActive())
                continue;
            if (p.getName().toLowerCase().startsWith(prefix)) {
                result.add(p);
            }
        }
        return result;
    }

    public Product getById(String id) {
        int index = getBucketIndex(id);
        HashNode<String, Product> current = hashTable[index];
        while (current != null) {
            if (current.key.equals(id)) {
                if (current.value.isActive()) {
                    return current.value;
                }
            }
            current = current.next;
        }
        return null;
    }
}
