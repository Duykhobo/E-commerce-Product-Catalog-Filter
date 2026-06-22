package datastructure.trie;

import entity.Product;
import datastructure.list.ProductLinkedList;

public class TrieNode {
    // Không dùng HashMap cho con, dùng mảng cố định 256 ký tự (ASCII) để tự build
    public TrieNode[] children;
    public boolean isEndOfWord;
    
    // Lưu danh sách sản phẩm khớp với từ khóa kết thúc tại node này
    // Sử dụng danh sách liên kết tự build
    public ProductLinkedList products;

    public TrieNode() {
        this.children = new TrieNode[256];
        this.isEndOfWord = false;
        this.products = new ProductLinkedList();
    }
}
