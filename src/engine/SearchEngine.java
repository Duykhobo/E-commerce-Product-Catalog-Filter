package engine;

import datastructure.array.ProductArray;
import datastructure.hash.HashNode;
import entity.Product;

public class SearchEngine {

    public HashNode[] hashTable;
    public int capacity;
    public ProductArray productArray;

    public SearchEngine(int tableCapacity) {
        this.capacity = tableCapacity;
        HashNode[] newTable = new HashNode[tableCapacity];
        this.hashTable = newTable;
        this.productArray = new ProductArray();
    }

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
            HashNode current = hashTable[index];

            while (current != null) {
                // Trường hợp ID đã tồn tại -> Cập nhật lại giá trị (Value) mới
                if (current.getKey().equals(key)) {
                    current.setValue(product);
                    return;
                }
                if (current.getNext() == null) {
                    current.setNext(new HashNode(key, product));
                    return;
                }
                current = current.getNext();
            }
        }
    }

    public void insertToArray(String name, Product product) {
        if (product == null) {
            return;
        }
        for (int i = 0; i < productArray.size; i++) {
            Product p = productArray.get(i);
            if (p != null && p.getId().equalsIgnoreCase(product.getId())) {
                productArray.data[i] = product;
                return;
            }
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
            Product p = productArray.get(i);
            if (p == null || !p.isActive()) {
                continue;
            }

            if (p.getName().toLowerCase().startsWith(prefix)) {
                result.add(p);
            }
        }
        return result;
    }

    public Product getById(String id) {
        int index = getBucketIndex(id);

        HashNode current = hashTable[index];

        while (current != null) {
            if (current.getKey().equals(id)) {
                Product p = (Product) current.getValue();
                if (p.isActive()) {
                    return p;
                }
            }
            current = current.getNext();
        }
        return null;
    }

    public Product getByIdIncludingInactive(String id) {
        int index = getBucketIndex(id);
        HashNode current = hashTable[index];
        while (current != null) {
            if (current.getKey().equals(id)) {
                return (Product) current.getValue();
            }
            current = current.getNext();
        }
        return null;
    }
}
