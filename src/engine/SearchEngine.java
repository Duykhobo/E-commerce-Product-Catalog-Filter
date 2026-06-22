package engine;

import java.util.*;

import datastructure.hash.HashNode;
import datastructure.list.ProductLinkedList;
import datastructure.list.ProductNode;
import datastructure.trie.TrieNode;
import entity.Product;

public class SearchEngine {
    public HashNode<String, Product>[] hashTable;
    public int capacity;
    public TrieNode trieRoot;

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
        if (key == null || product == null)
            return;
        int index = getBucketIndex(key);

        // bucket đang trống thì tạo node đầu tiên
        if (hashTable[index] == null) {
            hashTable[index] = new HashNode<>(key, product);
        } else {
            // bucket đã có node thì duyệt linked list tại bucket đó
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

    // TODO (Nguyễn Ngọc Minh Tân): Triển khai thuật toán chèn tên sản phẩm vào cây
    // Trie
    public void insertToTrie(String name, Product product) {
        if (name == null || name.isEmpty() || product == null) {
            return;
        }
        name = name.toLowerCase();
        int validCharCount = 0;
        TrieNode current = this.trieRoot;
        for (int i = 0; i < name.length(); i++) {
            char c = name.charAt(i);
            int index = (int) c;
            if (index >= 256) {
                continue;
            }
            if (current.children[index] == null) {
                current.children[index] = new TrieNode();
            }
            current = current.children[index];
            validCharCount++;
        }
        if (validCharCount > 0) {
            current.isEndOfWord = true;
            current.products.add(product);
        }
    }

    public ProductLinkedList searchByPrefix(String prefix) {
        ProductLinkedList result = new ProductLinkedList();

        if (prefix == null) {
            return result; // prefix null => không có kết quả
        }

        prefix = prefix.toLowerCase(); // chuẩn hoá prefix để tìm chính xác

        TrieNode current = trieRoot; // bắt đầu từ gốc trie

        for (int i = 0; i < prefix.length(); i++) {
            char c = prefix.charAt(i);
            int index = (int) c; // chuyển ký tự thành chỉ số trong mảng con

            if (index >= 256) {
                continue;
            }
            
            if (current.children[index] == null) {
                return result; // nếu nhánh không tồn tại thì không tìm được prefix
            }

            current = current.children[index]; // đi tiếp theo ký tự
        }

        collectProducts(current, result); // thu thập sản phẩm từ subtree bắt đầu tại nút tìm được

        return result; // trả về danh sách các sản phẩm khớp prefix
    }

    private void collectProducts(TrieNode node, ProductLinkedList result) {
        if (node == null) {
            return; // nếu nút null thì không còn gì để duyệt
        }

        if (node.isEndOfWord) {
            ProductNode current = node.products.head;
            while (current != null) {
                result.add(current.data); // thêm sản phẩm vào kết quả
                current = current.next; // đi tiếp đến sản phẩm tiếp theo trong danh sách
            }
        }

        for (int i = 0; i < 256; i++) {
            if (node.children[i] != null) {
                collectProducts(node.children[i], result); // tiếp tục duyệt các nhánh con
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
