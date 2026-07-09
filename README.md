# E-commerce Product Catalog Filter (CSD Project)

Dự án này là đồ án thực hành môn **Cấu trúc Dữ liệu và Giải thuật (CSD)**. Mục tiêu của dự án là xây dựng một hệ thống quản lý và lọc danh mục sản phẩm thương mại điện tử **từ con số 0**, bám sát yêu cầu môn học bằng việc **tự triển khai 100%** các cấu trúc dữ liệu, hoàn toàn **không** sử dụng các Collection có sẵn của Java (như `ArrayList`, `LinkedList`, `HashMap`, `Queue`) hay Generics (`<T>`).

Hệ thống được trang bị giao diện dòng lệnh (CLI) trực quan, có màu sắc sinh động, quản lý bộ dữ liệu 50 sản phẩm thực tế.

## 🎯 Cấu trúc Dữ liệu Ứng dụng

Đồ án triển khai thành công 5 cấu trúc dữ liệu cốt lõi vào các chức năng thực tiễn:

1. **Hash Table (Bảng băm):** 
   - Ứng dụng để tra cứu siêu tốc `O(1)` sản phẩm theo mã ID.
   - Ứng dụng để lọc các sản phẩm theo chính xác 1 mức điểm Đánh giá (Rating).
2. **Binary Search Tree - BST (Cây tìm kiếm nhị phân):** 
   - Ứng dụng để lọc danh sách sản phẩm nằm trong một **Khoảng giá (Price Range)** và tự động trả về kết quả được sắp xếp tăng dần nhờ thuật toán In-Order.
3. **Circular Queue (Hàng đợi vòng tĩnh):** 
   - Ứng dụng để lưu trữ và hiển thị **Lịch sử tìm kiếm gần đây** (giới hạn số lượng lưu trữ, tự động ghi đè vòng tròn).
4. **Singly Linked List (Danh sách liên kết đơn):** 
   - Ứng dụng để quản lý **Giỏ hàng (Shopping Cart)** với khả năng thêm bớt số lượng linh hoạt vô hạn.
5. **Dynamic Array (Mảng động):** 
   - Ứng dụng để làm mảng trả về kết quả chung, đồng thời phục vụ tính năng **Tìm kiếm tự động (Autocomplete)** thông qua việc duyệt mảng so khớp chuỗi.

*(Để xem giải thích chi tiết về độ phức tạp Big O, thuật toán và ưu nhược điểm, vui lòng tham khảo file `TongHop_CauTrucDuLieu.md`)*

## 📂 Cấu trúc Mã nguồn

Mã nguồn được phân tách rõ ràng theo chuẩn thiết kế Facade và Module để đảm bảo tính dễ đọc và mở rộng:

*   **`data.txt`**: File Text lưu trữ giả lập cơ sở dữ liệu gồm 50 sản phẩm đa dạng.
*   **`src/App.java`**: Lớp Main điều khiển giao diện Menu CLI (hỗ trợ màu ANSI), tương tác với người dùng.
*   **`src/entity/Product.java`**: Lớp thực thể đại diện cho Sản phẩm (ID, Tên, Giá, Đánh giá, Trạng thái Active/Xóa).
*   **`src/core/CatalogFilterSystem.java`**: Lớp Facade, nhạc trưởng điều phối toàn bộ các Engine bên dưới.
*   **`src/engine/`**: Nơi thực thi 5 Engine lõi:
    *   `PriceEngine.java`: Quản lý Cây BST cho bài toán Giá.
    *   `RatingEngine.java`: Quản lý Hash Table cho bài toán Điểm đánh giá.
    *   `SearchEngine.java`: Quản lý Hash Table (ID) và Mảng (Autocomplete).
    *   `SearchHistory.java`: Quản lý Queue cho bài toán Lịch sử.
    *   `ShoppingCart.java`: Quản lý Linked List cho Giỏ hàng.
*   **`src/datastructure/`**: Nơi chứa lõi của các Node:
    *   `/array/ProductArray.java`: Cấu trúc Mảng động.
    *   `/hash/HashNode.java`: Cấu trúc Node cho Hash Table (Chaining).
    *   `/tree/`: Chứa `TreeNode` và `PriceNode` cho Cây nhị phân.

## 🚀 Hướng dẫn chạy chương trình

Dự án có sử dụng ký tự UTF-8 và màu sắc ANSI, do đó bạn nên chạy bằng Terminal của IDE (VSCode Terminal, IntelliJ Terminal) hoặc Git Bash / PowerShell để hiển thị đẹp nhất.

1. Di chuyển vào thư mục gốc của dự án.
2. Biên dịch toàn bộ source code với cờ `UTF-8`:
   ```bash
   javac -encoding UTF-8 -d bin -sourcepath src src/App.java
   ```
3. Chạy chương trình:
   ```bash
   java -cp bin App
   ```
4. Trải nghiệm Menu 11 tính năng cực kỳ xịn xò!
