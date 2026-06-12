package engine;

import entity.Product;

public class RatingSorter {
    public Product[] heapArray;
    public int size;
    public int capacity;

    public RatingSorter(int capacity) {
        this.capacity = capacity;
        this.heapArray = new Product[capacity];
        this.size = 0;
    }

    // TODO (Trịnh Lê Thiên Quân): Triển khai chèn Product vào Max-Heap (sift-up)
    public void insertProduct(Product p) {
        // ...
    }

    // TODO (Trịnh Lê Thiên Quân): Viết hàm heapify nếu cần tái cân bằng heap tại một index
    public void heapify(int index) {
        // ...
    }

    // Snippet đã cung cấp trong báo cáo
    public Product extractMax() {
        if (size == 0) {
            return null;
        }
        Product topProduct = heapArray[0];
        heapArray[0] = heapArray[size - 1];
        heapArray[size - 1] = null; 
        size--;
        
        int currentIndex = 0;
        
        while (true) {
            int leftChildIndex = 2 * currentIndex + 1;
            int rightChildIndex = 2 * currentIndex + 2;
            int largestIndex = currentIndex;
            
            if (leftChildIndex < size && 
                heapArray[leftChildIndex].getRating() > heapArray[largestIndex].getRating()) {
                largestIndex = leftChildIndex;
            }
            if (rightChildIndex < size && 
                heapArray[rightChildIndex].getRating() > heapArray[largestIndex].getRating()) {
                largestIndex = rightChildIndex;
            }
            if (largestIndex == currentIndex) {
                break;
            }
            Product temp = heapArray[currentIndex];
            heapArray[currentIndex] = heapArray[largestIndex];
            heapArray[largestIndex] = temp;
            
            currentIndex = largestIndex;
        }
        return topProduct;
    }
}
