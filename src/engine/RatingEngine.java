package engine;

import datastructure.array.ProductArray;
import datastructure.hash.HashNode;
import entity.Product;
import utils.ValidationUtils;

// Lớp này triển khai cấu trúc Hash Table (Bảng Băm) để lọc sản phẩm theo chính xác mức Rating (Đánh giá)
public class RatingEngine {
    public HashNode[] hashTable; // Mảng chứa các Node của Bảng Băm
    public int capacity;         // Kích thước của mảng

    public RatingEngine(int capacity) {
        this.capacity = capacity;
        this.hashTable = new HashNode[capacity]; // Khởi tạo mảng Bảng băm
    }

    // Hàm tính Hash Index từ một số thực (double rating)
    private int getBucketIndex(double rating) {
        // Sử dụng hàm băm có sẵn của class Double trong Java để chuyển số thực thành số nguyên
        int hashCode = Double.valueOf(rating).hashCode();
        
        // Lấy giá trị tuyệt đối để tránh index bị âm
        int absHashCode = Math.abs(hashCode);
        if (absHashCode < 0) {
            absHashCode = Integer.MAX_VALUE;
        }
        
        // Chia lấy dư cho capacity để ra vị trí hợp lệ trong mảng [0, capacity - 1]
        return absHashCode % capacity;
    }

    // Chèn một sản phẩm vào Bảng băm dựa trên Rating của nó
    public void insertProduct(Product product) {
        ValidationUtils.validateNotNull(product, "Sản phẩm không được null");
        ValidationUtils.validateRating(product.getRating());
        
        double rating = product.getRating(); // Lấy Rating làm Khóa (Key)
        int index = getBucketIndex(rating);  // Tính vị trí Index

        HashNode current = hashTable[index]; // Lấy danh sách liên kết tại vị trí Index
        
        // BƯỚC 1: KIỂM TRA ĐỤNG ĐỘ & TÌM KIẾM
        // Trải qua danh sách liên kết tại bucket này để tìm xem mức rating này đã từng được lưu chưa
        while (current != null) {
            if (current.getKey().equals(rating)) {
                // TRƯỜNG HỢP 1: Đã tồn tại mức rating này! 
                // Ví dụ: Đã có giỏ chứa các sản phẩm 4.5 sao.
                // Giải pháp: Ép kiểu Value về ProductArray và tống sản phẩm mới vào mảng đó (O(1))
                ((ProductArray) current.getValue()).add(product);
                return; // Xong, thoát hàm
            }
            current = current.getNext(); // Di chuyển sang Node tiếp theo nếu không khớp
        }

        // BƯỚC 2: CHƯA TỒN TẠI MỨC RATING NÀY
        // Ví dụ: Lần đầu tiên có sản phẩm đạt 4.5 sao
        // Tạo một mảng động mới (ProductArray) để chứa các sản phẩm 4.5 sao
        ProductArray newArray = new ProductArray();
        newArray.add(product); // Bỏ sản phẩm đầu tiên vào
        
        // Tạo một HashNode mới (Key = 4.5, Value = mảng chứa sản phẩm)
        HashNode newNode = new HashNode(rating, newArray);
        
        // Kỹ thuật Insert at Head (Chèn vào đầu danh sách liên kết) để xử lý đụng độ
        // Cho Node mới trỏ vào Node đầu hiện tại của danh sách
        newNode.setNext(hashTable[index]);
        // Cập nhật lại mảng tại vị trí index trỏ vào Node mới
        hashTable[index] = newNode;
    }

    // Truy xuất mảng sản phẩm theo mức rating (Độ phức tạp O(1) trung bình)
    public ProductArray getProductsByRating(double rating) {
        ValidationUtils.validateRating(rating);
        // Tính Index nhanh chóng
        int index = getBucketIndex(rating);
        HashNode current = hashTable[index];
        
        // Tìm kiếm trong danh sách liên kết tại vị trí đó
        while (current != null) {
            if (current.getKey().equals(rating)) {
                // Nếu tìm đúng mức Rating, trả về mảng sản phẩm (ép kiểu từ Object sang ProductArray)
                return (ProductArray) current.getValue();
            }
            current = current.getNext();
        }
        
        // Trả về mảng rỗng nếu không tìm thấy bất kỳ sản phẩm nào có mức rating này
        return new ProductArray();
    }
}
