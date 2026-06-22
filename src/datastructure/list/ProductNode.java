package datastructure.list;

import entity.Product;

public class ProductNode {
    public Product data;
    public ProductNode next;

    public ProductNode(Product data) {
        this.data = data;
        this.next = null;
    }
}
