package engine;

import datastructure.tree.TreeNode;
import entity.Product;
import java.util.List;

public class PriceEngine {
    public TreeNode<Product> root;

    public PriceEngine() {
        this.root = null;
    }

    // TODO (Trịnh Lê Thiên Quân): Triển khai thuật toán chèn Product vào cây BST theo giá (price)
    public void insertProduct(Product p) {
        // ...
    }

    // TODO (Nguyễn Thanh Duy): Đọc hiểu và tinh chỉnh logic Duyệt cây (In-Order) để lọc giá
    public void searchByPriceRange(TreeNode<Product> node, double min, double max, List<Product> result) {
        
        
    }
}
