package engine;

// Lớp này triển khai cấu trúc dữ liệu Circular Queue (Hàng đợi vòng) bằng mảng 1 chiều
public class SearchHistory {
    private String[] queue; // Mảng 1 chiều lưu trữ các từ khóa tìm kiếm (dạng chuỗi String)
    private int front;      // Con trỏ trỏ đến vị trí của phần tử CŨ NHẤT (Đầu hàng đợi)
    private int rear;       // Con trỏ trỏ đến vị trí của phần tử MỚI NHẤT (Cuối hàng đợi)
    private int size;       // Biến đếm số lượng từ khóa HIỆN TẠI đang có trong hàng đợi
    private int capacity;   // Sức chứa TỐI ĐA của hàng đợi (ví dụ: chỉ lưu 5 từ khóa)

    // Hàm khởi tạo (Constructor) nhận vào sức chứa tối đa
    public SearchHistory(int capacity) {
        this.capacity = capacity;               // Gán sức chứa tối đa
        this.queue = new String[capacity];      // Khởi tạo mảng String với kích thước = capacity
        this.front = 0;                         // Ban đầu hàng đợi rỗng, front nằm ở vị trí 0
        this.rear = -1;                         // Ban đầu hàng đợi rỗng, chưa có phần tử cuối nên rear = -1
        this.size = 0;                          // Số lượng phần tử ban đầu = 0
    }

    // Thêm từ khóa vào lịch sử (Thao tác Enqueue trong Circular Queue)
    public void addSearchTerm(String term) {
        // Kiểm tra tính hợp lệ của dữ liệu đầu vào. Nếu rỗng thì bỏ qua không thêm
        if (term == null || term.isEmpty()) {
            return; // Dừng hàm
        }
        
        // KIỂM TRA HÀNG ĐỢI ĐẦY (Circular logic)
        // Nếu số lượng phần tử hiện tại đã đạt tới sức chứa tối đa...
        if (size == capacity) {
            // Nhích con trỏ front lên 1 vị trí theo vòng tròn (Modulo cho capacity). 
            // Việc nhích front đồng nghĩa với việc ta "bỏ qua" và xóa phần tử cũ nhất khỏi Queue.
            front = (front + 1) % capacity;
            size--; // Giảm size đi 1 để lát nữa thêm phần tử mới không bị vượt capacity
        }
        
        // THÊM PHẦN TỬ VÀO CUỐI HÀNG ĐỢI
        // Nhích con trỏ rear lên 1 vị trí theo vòng tròn (Modulo cho capacity)
        rear = (rear + 1) % capacity;
        // Gán từ khóa vừa tìm kiếm vào vị trí rear mới
        queue[rear] = term;
        // Tăng số lượng phần tử lên 1
        size++;
    }

    // Trả về danh sách lịch sử tìm kiếm từ MỚI NHẤT đến CŨ NHẤT để hiển thị ra màn hình
    public String[] getRecentSearches() {
        // Tạo một mảng kết quả có kích thước bằng chính số lượng phần tử hiện tại đang có
        String[] result = new String[size];
        int count = 0; // Biến đếm số phần tử đã duyệt qua
        
        // Bắt đầu duyệt ngược từ vị trí rear (phần tử mới nhất được thêm vào)
        int current = rear;
        
        // Lặp cho đến khi duyệt đủ số lượng phần tử có trong Queue
        while (count < size) {
            // Xử lý hiệu ứng vòng lặp (vòng ngược). 
            // Nếu current lùi quá mảng (nhỏ hơn 0), quay vòng nó về vị trí cuối mảng (capacity - 1)
            if (current < 0) {
                current = capacity - 1;
            }
            // Lấy từ khóa tại vị trí current đưa vào mảng kết quả, sau đó tăng count lên
            result[count++] = queue[current];
            // Lùi current xuống 1 để tiếp tục lấy phần tử cũ hơn
            current--;
        }
        // Trả về mảng chứa lịch sử đã sắp xếp từ mới -> cũ
        return result;
    }

    // Hàm xóa toàn bộ lịch sử tìm kiếm
    public void clear() {
        front = 0;   // Đưa front về 0
        rear = -1;   // Đưa rear về -1 như lúc mới khởi tạo
        size = 0;    // Reset lại số lượng = 0
        // (Không cần xóa hẳn giá trị trong mảng queue vì khi size=0, các hàm khác sẽ ghi đè lên)
    }
}
