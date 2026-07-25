package engine;

import datastructure.array.ProductArray;
import datastructure.tree.PriceNode;
import entity.Product;
import utils.ValidationUtils;

public class PriceEngine {

    public PriceNode root;
    private int size;

    public PriceEngine() {
        this.root = null;
        this.size = 0;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    private int compare(double p1, double p2) {
        if (Math.abs(p1 - p2) < 1e-9) {
            return 0;
        }
        return p1 < p2 ? -1 : 1;
    }

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
        int cmp = 0;

        while (current != null) {
            parent = current;
            cmp = compare(p.getPrice(), current.getPrice());

            if (cmp == 0) {
                current.addProduct(p);
                size++;
                return;
            } else if (cmp < 0) {
                current = current.getLeft();
            } else {
                current = current.getRight();
            }
        }

        PriceNode newNode = new PriceNode(p.getPrice());
        newNode.addProduct(p);

        if (cmp < 0) {
            parent.setLeft(newNode);
        } else {
            parent.setRight(newNode);
        }
        size++;
    }

    public void searchByPriceRange(PriceNode node, double min, double max, ProductArray result) {
        ValidationUtils.validatePriceRange(min, max);
        if (node == null) {
            return;
        }

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
