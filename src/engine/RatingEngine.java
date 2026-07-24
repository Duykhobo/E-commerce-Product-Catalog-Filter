package engine;

import datastructure.array.ProductArray;
import datastructure.hash.HashNode;
import entity.Product;
import utils.ValidationUtils;

public class RatingEngine {

    public HashNode[] hashTable;
    public int capacity;

    public RatingEngine(int capacity) {
        this.capacity = capacity;
        this.hashTable = new HashNode[capacity];
    }

    private int getBucketIndex(double rating) {
        int hashCode = Double.valueOf(rating).hashCode();

        int absHashCode = Math.abs(hashCode);
        if (absHashCode < 0) {
            absHashCode = Integer.MAX_VALUE;
        }

        return absHashCode % capacity;
    }

    public void insertProduct(Product product) {
        ValidationUtils.validateNotNull(product, "Sản phẩm không được null");
        ValidationUtils.validateRating(product.getRating());

        double rating = product.getRating();
        int index = getBucketIndex(rating);

        HashNode current = hashTable[index];

        // BƯỚC 1: KIỂM TRA ĐỤNG ĐỘ & TÌM KIẾM
        while (current != null) {
            if (current.getKey().equals(rating)) {
                // TRƯỜNG HỢP 1: Đã tồn tại mức rating này!
                ((ProductArray) current.getValue()).add(product);
                return;
            }
            current = current.getNext();
        }

        // BƯỚC 2: CHƯA TỒN TẠI MỨC RATING NÀY
        ProductArray newArray = new ProductArray();
        newArray.add(product); // Bỏ sản phẩm đầu tiên vào

        HashNode newNode = new HashNode(rating, newArray);

        newNode.setNext(hashTable[index]);
        hashTable[index] = newNode;
    }

    public ProductArray getProductsByRating(double rating) {
        ValidationUtils.validateRating(rating);
        int index = getBucketIndex(rating);
        HashNode current = hashTable[index];

        while (current != null) {
            if (current.getKey().equals(rating)) {
                return (ProductArray) current.getValue();
            }
            current = current.getNext();
        }
        return new ProductArray();
    }
}
