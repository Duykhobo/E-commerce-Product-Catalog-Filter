package datastructure.trie;

import entity.Product;
import java.util.ArrayList;
import java.util.List;

public class TrieNode {
    public TrieNode[] children;
    public boolean isEndOfWord;
    // Không được dùng List có sẵn theo luật CSD? Nhưng theo Report thiết kế là "productList: List<Product>"
    // Do báo cáo ghi rõ "productList: List<Product>", ta có thể mượn List của Java hoặc nếu khắt khe thì bạn tự viết Linked List.
    // Tạm thời dùng ArrayList để đúng với thiết kế "List<Product>".
    public List<Product> productList;

    public TrieNode() {
        // Giả định bảng chữ cái tiếng Anh in thường (a-z) có 26 ký tự
        this.children = new TrieNode[26];
        this.isEndOfWord = false;
        this.productList = new ArrayList<>();
    }
}
