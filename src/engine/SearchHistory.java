package engine;

// Cấu trúc dữ liệu Circular Queue (Hàng đợi vòng) bằng mảng 1 chiều
public class SearchHistory {
    private String[] queue; // Lưu các từ khóa
    private int front;      // Phần tử cũ nhất
    private int rear;       // Phần tử mới nhất
    private int size;       // Số lượng hiện tại
    private int capacity;   // Sức chứa tối đa

    public SearchHistory(int capacity) {
        this.capacity = capacity;
        this.queue = new String[capacity];
        this.front = 0;
        this.rear = -1;
        this.size = 0;
    }

    // Thêm từ khóa vào lịch sử
    public void addSearchTerm(String term) {
        if (term == null || term.isEmpty()) return;
        
        // Nếu đầy, loại bỏ phần tử cũ nhất
        if (size == capacity) {
            front = (front + 1) % capacity;
            size--;
        }
        
        // Thêm phần tử mới vào cuối
        rear = (rear + 1) % capacity;
        queue[rear] = term;
        size++;
    }

    // Lấy lịch sử từ mới nhất đến cũ nhất
    public String[] getRecentSearches() {
        String[] result = new String[size];
        int count = 0;
        int current = rear;
        
        // Duyệt ngược từ rear
        while (count < size) {
            result[count++] = queue[current];
            current = (current - 1 + capacity) % capacity; // Xử lý vòng ngược
        }
        return result;
    }

    // Xóa lịch sử
    public void clear() {
        front = 0;
        rear = -1;
        size = 0;
    }
}
