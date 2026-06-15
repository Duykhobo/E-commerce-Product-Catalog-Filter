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

    public int getBucketIndex(String key) {
        return Math.abs(key.hashCode()) % capacity;
    }


    // TODO (Phan Khánh Duy): Viết thuật toán chèn Product vào hashTable và xử lý
    // đụng độ (Chaining)
    public void put(String key, Product product) {
        int index = getBucketIndex(key);
        //kiểm tra toàn bộ mảng, nếu trống => tạo node mới 
        if(hashTable[index] == null) {
            hashTable[index] = new HashNode<>(key, product);
        } else {
            HashNode<String, Product> current = hashTable[index];
            //nếu xảy ra va chạm, dùng một vòng lặp để duyệt qua các node trong bucket đó
            while (current != null) {
                //trong khi duyệt nếu tìm thấy node nào key trùng với key cần chèn thì ghi đè sản phẩm
                if (current.key.equals(key)) {
                    current.value = product; // Cập nhật giá trị nếu key đã tồn tại
                    return;
                }
                //nếu duyệt đến node cuối mà không thấy key trùng với ID thì tạo hashNode mới và thêm vào cuối danh sách liên kết
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
}
