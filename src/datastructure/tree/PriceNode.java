package datastructure.tree;

import datastructure.array.ProductArray;
import entity.Product;

public class PriceNode {
    private double price;
    private ProductArray products;
    private PriceNode left;
    private PriceNode right;

    public PriceNode(double price) {
        this.price = price;
        this.products = new ProductArray();
        this.left = null;
        this.right = null;
    }

    public void addProduct(Product p) {
        if (p != null) {
            products.add(p);
        }
    }

    public double getPrice() {
        return price;
    }

    public ProductArray getProducts() {
        return products;
    }

    public PriceNode getLeft() {
        return left;
    }

    public void setLeft(PriceNode left) {
        this.left = left;
    }

    public PriceNode getRight() {
        return right;
    }

    public void setRight(PriceNode right) {
        this.right = right;
    }
}
