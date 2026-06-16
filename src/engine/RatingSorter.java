package engine;

import entity.Product;

public class RatingSorter {
    // Tự build cấu trúc Max-Heap bằng mảng (Array), không dùng PriorityQueue có sẵn
    public Product[] heap;
    public int size;
    public int capacity;

    public RatingSorter(int capacity) {
        this.capacity = capacity;
        this.heap = new Product[capacity];
        this.size = 0;
    }

    // Các hàm helper hỗ trợ tính toán vị trí trên mảng
    private int parent(int i) { return (i - 1) / 2; }
    private int leftChild(int i) { return (2 * i) + 1; }
    private int rightChild(int i) { return (2 * i) + 2; }
    private void swap(int i, int j) {
        Product temp = heap[i];
        heap[i] = heap[j];
        heap[j] = temp;
    }

    //hàm phụ trợ nếu đã max capacity muốn mở rộng thêm
    private void resize(){
        this.capacity = this.capacity * 2;
        Product[] newHeap = new Product[this.capacity];

        for(int i = 0 ; i < this.size; i++){
            newHeap[i] = heap[i];
        }
        this.heap = newHeap;
    }




    // TODO (Trịnh Lê Thiên Quân): Viết hàm thêm sản phẩm vào mảng và thực hiện Heapify Up (chìm lên) dựa theo Rating
    public void insert(Product product) {
        // kiểm tra null
        if(product == null){
            return;
        }
        // Kiểm tra mảng đầy
        if (size == capacity) {
            resize();
        }
        
        //Thêm vào cuối mảng và tăng size
        heap[size] = product;
        int currentIdx = size;
        size++;
        
        // Heapify Up
        while (currentIdx > 0) {
            int parentIdx = parent(currentIdx);
            
            if (heap[currentIdx].getRating() > heap[parentIdx].getRating()) {
                swap(currentIdx, parentIdx);
                currentIdx = parentIdx;
            } else {
                break;
            }
        }
    }

    // TODO (Nguyễn Thanh Duy): Viết hàm trích xuất sản phẩm có Rating cao nhất (Extract Max) và Heapify Down
    public Product extractMax() {
        // 1. Lấy phần tử gốc (heap[0])
        // 2. Lấy phần tử cuối cùng đắp lên gốc
        // 3. Giảm size
        // 4. Gọi đệ quy hoặc vòng lặp để đưa phần tử gốc chìm xuống đúng vị trí (Heapify Down)
        return null;
    }
}
