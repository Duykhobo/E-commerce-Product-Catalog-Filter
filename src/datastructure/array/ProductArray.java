package datastructure.array;

import entity.Product;
import java.util.Iterator;

public class ProductArray implements Iterable<Product> {
    public Product[] data;
    public int size;
    public int capacity;

    public ProductArray() {
        this.capacity = 10;
        this.data = new Product[capacity];
        this.size = 0;
    }

    public void add(Product product) {
        if (size == capacity) {
            resize();
        }
        data[size] = product;
        size++;
    }

    private void resize() {
        capacity = capacity * 2;
        Product[] newData = new Product[capacity];
        for (int i = 0; i < size; i++) {
            newData[i] = data[i];
        }
        data = newData;
    }

    public Product get(int index) {
        if (index < 0 || index >= size) {
            return null;
        }
        return data[index];
    }

    @Override
    public Iterator<Product> iterator() {
        return new Iterator<Product>() {
            private int currentIndex = 0;

            @Override
            public boolean hasNext() {
                return currentIndex < size;
            }

            @Override
            public Product next() {
                return data[currentIndex++];
            }
        };
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i < size; i++) {
            sb.append(data[i].toString());
            if (i < size - 1) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }
}
