package engine;

import datastructure.linkedlist.CartNode;
import entity.Product;

public class ShoppingCart {

    private CartNode head;
    private CartNode tail;
    private int totalItems;

    public ShoppingCart() {
        this.head = null;
        this.tail = null;
        this.totalItems = 0;
    }

    public void addProduct(Product p, int quantity) {
        if (p == null || quantity <= 0) {
            return;
        }

        // BƯỚC 1: Kiểm tra sản phẩm đã tồn tại chưa
        CartNode current = head;
        while (current != null) {
            if (current.getProduct().getId().equals(p.getId())) {
                current.setQuantity(current.getQuantity() + quantity);
                return;
            }
            current = current.getNext();
        }

        // BƯỚC 2: Thêm mới vào CUỐI danh sách (Insert at Tail)
        CartNode newNode = new CartNode(p, quantity);
        if (head == null) {
            head = newNode;
            tail = newNode;
        } else {
            tail.setNext(newNode);
            tail = newNode;
        }

        totalItems++;
    }

    public void removeProduct(String productId) {
        if (head == null) {
            return;
        }

        // BƯỚC 1: Xóa ở ĐẦU danh sách
        if (head.getProduct().getId().equals(productId)) {
            head = head.getNext();
            if (head == null) {
                tail = null;
            }
            totalItems--;
            return;
        }

        // BƯỚC 2: Xóa ở GIỮA hoặc CUỐI
        CartNode current = head;
        while (current.getNext() != null) {
            if (current.getNext().getProduct().getId().equals(productId)) {
                if (current.getNext() == tail) {
                    tail = current;
                }
                current.setNext(current.getNext().getNext());
                totalItems--;
                return;
            }
            current = current.getNext();
        }
    }

    public double calculateTotal() {
        double total = 0;
        CartNode current = head;

        while (current != null) {
            total += current.getProduct().getPrice() * current.getQuantity();
            current = current.getNext();
        }
        return total;
    }

    public void clearCart() {
        head = null;
        tail = null;
        totalItems = 0;
    }
}

