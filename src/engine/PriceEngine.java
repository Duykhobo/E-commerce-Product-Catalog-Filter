package engine;

import datastructure.list.ProductLinkedList;

import datastructure.tree.TreeNode;
import entity.Product;

public class PriceEngine {
    public TreeNode<Product> root;
    private int size;

    public PriceEngine() {
        this.root = null;
        this.size = 0;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    // TODO (Trịnh Lê Thiên Quân): Triển khai thuật toán chèn Product vào cây BST
    // theo giá (price)
    public void insertProduct(Product p) {
        TreeNode<Product> newNode = new TreeNode<Product>(p);
        if (isEmpty()) {
            root = newNode;
            size++;
        } else {
            TreeNode<Product> current = root;
            TreeNode<Product> parent = current;
            while (current != null) {
                parent = current;
                if (current.getData().getPrice() <= newNode.getData().getPrice()) {
                    current = current.getRight();
                } else {
                    current = current.getLeft();
                }
            }
            if (parent.getData().getPrice() <= newNode.getData().getPrice()) {
                parent.setRight(newNode);
            } else {
                parent.setLeft(newNode);
            }
            size++;
        }
    }

    // Đã hoàn thành: Đọc hiểu và tinh chỉnh logic Duyệt cây (In-Order) để lọc giá
    public void searchByPriceRange(TreeNode<Product> node, double min, double max, ProductLinkedList result) {
        if (node == null) {
            return;
        }

        double currentPrice = node.getData().getPrice();

        // 1. Duyệt nhánh trái nếu có khả năng chứa giá trị >= min
        if (currentPrice > min) {
            searchByPriceRange(node.getLeft(), min, max, result);
        }

        // 2. Thêm vào kết quả nếu thoả mãn điều kiện
        if (currentPrice >= min && currentPrice <= max) {
            result.add(node.getData());
        }

        // 3. Duyệt nhánh phải nếu có khả năng chứa giá trị <= max
        if (currentPrice <= max) {
            searchByPriceRange(node.getRight(), min, max, result);
        }
    }
}
