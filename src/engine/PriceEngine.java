package engine;

import datastructure.array.ProductArray;
import datastructure.tree.PriceNode;
import entity.Product;
import utils.ValidationUtils;

// Cấu trúc Binary Search Tree (BST) quản lý sản phẩm theo giá
public class PriceEngine {
    public PriceNode root; // Node gốc
    private int size;      // Tổng số lượng sản phẩm

    public PriceEngine() {
        this.root = null;
        this.size = 0;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    // Hàm so sánh an toàn cho kiểu double (tránh sai số IEEE 754)
    private int compare(double p1, double p2) {
        if (Math.abs(p1 - p2) < 1e-9) return 0; // Trùng giá
        return p1 < p2 ? -1 : 1;                // Nhỏ hơn hoặc Lớn hơn
    }

    // Chèn Product vào cây BST (O(log N))
    public void insertProduct(Product p) {
        ValidationUtils.validateNotNull(p, "Sản phẩm không được null");
        
        if (root == null) {
            root = new PriceNode(p.getPrice());
            root.addProduct(p);
            size++;
            return;
        }

        PriceNode current = root;
        PriceNode parent = null;
        int cmp = 0; // Biến lưu kết quả so sánh
        
        // Duyệt tìm vị trí chèn
        while (current != null) {
            parent = current;
            cmp = compare(p.getPrice(), current.getPrice());
            
            if (cmp == 0) {
                current.addProduct(p); // Giá trùng khớp
                size++;
                return;
            } else if (cmp < 0) {
                current = current.getLeft();  // Sang trái
            } else {
                current = current.getRight(); // Sang phải
            }
        }

        // Tạo Node mới tại vị trí lá
        PriceNode newNode = new PriceNode(p.getPrice());
        newNode.addProduct(p);
        
        // Liên kết với Node cha (Dựa vào kết quả so sánh cuối cùng)
        if (cmp < 0) {
            parent.setLeft(newNode);
        } else {
            parent.setRight(newNode);
        }
        size++;
    }

    // Tìm kiếm khoảng giá bằng In-Order Traversal (O(K + log N))
    public void searchByPriceRange(PriceNode node, double min, double max, ProductArray result) {
        ValidationUtils.validatePriceRange(min, max);
        if (node == null) return;

        double currentPrice = node.getPrice();

        // Bước 1: Nhánh trái (Cắt tỉa nếu giá <= min)
        if (currentPrice > min) {
            searchByPriceRange(node.getLeft(), min, max, result);
        }

        // Bước 2: Node hiện tại
        if (currentPrice >= min && currentPrice <= max) {
            ProductArray products = node.getProducts();
            for (int i = 0; i < products.size; i++) {
                Product p = products.get(i);
                if (p != null && p.isActive()) {
                    result.add(p);
                }
            }
        }

        // Bước 3: Nhánh phải (Cắt tỉa nếu giá >= max)
        if (currentPrice <= max) {
            searchByPriceRange(node.getRight(), min, max, result);
        }
    }
}
