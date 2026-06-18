package datastructure.list;

import entity.Product;
import java.util.Iterator;

public class ProductLinkedList implements Iterable<Product> {
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
    public Iterator<Product> iterator() {
        return new Iterator<Product>() {
            private ProductNode current = head;

            @Override
            public boolean hasNext() {
                return current != null;
            }

            @Override
            public Product next() {
                Product data = current.data;
                current = current.next;
                return data;
            }
        };
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
