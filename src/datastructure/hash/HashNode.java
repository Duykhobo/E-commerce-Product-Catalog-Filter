package datastructure.hash;

import entity.Product;

public class HashNode {
    private String key;
    private Product value;
    private HashNode next;

    public HashNode(String key, Product value) {
        this.key = key;
        this.value = value;
        this.next = null;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Product getValue() {
        return value;
    }

    public void setValue(Product value) {
        this.value = value;
    }

    public HashNode getNext() {
        return next;
    }

    public void setNext(HashNode next) {
        this.next = next;
    }
}
