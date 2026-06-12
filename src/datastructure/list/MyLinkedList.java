package datastructure.list;

public class MyLinkedList<T> {
    public Node<T> head;
    public Node<T> tail;

    public MyLinkedList() {
        head = null;
        tail = null;
    }

    public boolean isEmpty() {
        return head == null;
    }

    // TODO: Yêu cầu sinh viên triển khai thêm phần tử vào cuối danh sách liên kết
    public void addLast(T data) {
        // ...
    }

    // TODO: Yêu cầu sinh viên triển khai phần tìm kiếm phần tử
    public T search(String id) {
        // Lưu ý: T có thể là Product, cần ép kiểu hoặc dùng interface để lấy ID
        // ...
        return null;
    }

    // TODO: Yêu cầu sinh viên triển khai xóa phần tử
    public void remove(T data) {
        // ...
    }
    
    // TODO: Thêm các phương thức duyệt (traverse), hiển thị (display)...
    public void display() {
        // ...
    }
}
