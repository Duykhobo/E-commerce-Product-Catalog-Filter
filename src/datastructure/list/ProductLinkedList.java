package datastructure.list;

import entity.Product;

public class ProductLinkedList {
    public ProductNode head;
    public ProductNode tail;
    public int size;

    public ProductLinkedList() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    public void add(Product product) {
        ProductNode newNode = new ProductNode(product);
        if (head == null) {
            head = newNode;
            tail = newNode;
        } else {
            tail.next = newNode;
            tail = newNode;
        }
        size++;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        ProductNode current = head;
        while (current != null) {
            sb.append(current.data.toString());
            if (current.next != null) {
                sb.append(", ");
            }
            current = current.next;
        }
        sb.append("]");
        return sb.toString();
    }
}
