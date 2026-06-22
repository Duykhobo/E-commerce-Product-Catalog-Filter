# E-commerce Product Catalog Filter (CSD Project)

Dự án này là bài tập thực hành môn **Cấu trúc Dữ liệu và Giải thuật (CSD)**. Mục tiêu của dự án là xây dựng một hệ thống quản lý và lọc danh mục sản phẩm thương mại điện tử **từ con số 0**, hoàn toàn không sử dụng các cấu trúc dữ liệu có sẵn của Java (như `ArrayList`, `LinkedList`, `HashMap`, `PriorityQueue` v.v.).

## 🎯 Yêu cầu môn học

- Tự triển khai **100% thủ công** 4 cấu trúc dữ liệu cốt lõi (Data Structures): 
  1. **Mảng động (Dynamic Array - ProductArray)**
  2. **Bảng Băm (Hash Map)**
  3. **Cây tìm kiếm nhị phân (Binary Search Tree - BST)**
  4. **Hàng đợi ưu tiên (Max-Heap)**
- Ứng dụng các cấu trúc dữ liệu đã học để giải quyết bài toán thực tế: Tìm kiếm siêu tốc bằng ID, lọc sản phẩm theo khoảng giá, tìm kiếm chuỗi (Prefix search), và hiển thị top sản phẩm đánh giá cao.

## 📂 Cấu trúc mã nguồn hiện tại

Mã nguồn được phân tách rõ ràng theo chuẩn thiết kế Module để đảm bảo tính dễ đọc và dễ bảo trì:

*   **`src/entity/Product.java`**: Lớp đại diện cho Sản phẩm (ID, Tên, Giá, Đánh giá).
*   **`src/datastructure/`**: Nơi chứa lõi Cấu trúc dữ liệu "nhà làm":
    *   `/array/`: Cấu trúc Mảng động (Thay thế hoàn toàn cho `ArrayList` và `Trie`).
    *   `/hash/`: Cấu trúc Node cho Hash Map (Xử lý va chạm bằng Chaining).
    *   `/tree/`: Cấu trúc Node cho Cây nhị phân.
*   **`src/engine/`**: Nơi thực thi 4 thuật toán lõi của dự án:
    *   `PriceEngine.java`: Quản lý BST để lọc theo khoảng giá.
    *   `RatingSorter.java`: Quản lý Max-Heap để lấy Top Rating.
    *   `SearchEngine.java`: Quản lý Hash Map (tìm ID) và Trie (tìm Prefix).
*   **`src/core/CatalogFilterSystem.java`**: Lớp nghiệp vụ, đóng vai trò nhạc trưởng điều phối các Engine ở trên.
*   **`src/App.java`**: File Main chứa dữ liệu giả lập để test ứng dụng.

## 🚀 Hướng dẫn chạy
Sau khi các thành viên trong nhóm code xong các hàm `// TODO` trong các nhánh (branch) của mình và gộp code lại, bạn có thể chạy hàm `main` trong file `src/App.java` để kiểm thử toàn bộ kết quả.
