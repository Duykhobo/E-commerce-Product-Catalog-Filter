# E-commerce Product Catalog Filter (CSD Project)

Dự án này là bài tập thực hành môn **Cấu trúc Dữ liệu và Giải thuật (CSD)**. Mục tiêu của dự án là xây dựng một hệ thống quản lý và lọc danh mục sản phẩm thương mại điện tử **từ con số 0**, hoàn toàn không sử dụng các cấu trúc dữ liệu có sẵn của Java (như `ArrayList`, `LinkedList`, `HashMap`, v.v.).

## Yêu cầu môn học

- Tự triển khai các cấu trúc dữ liệu (Data Structures): **Danh sách liên kết (Linked List)**, **Cây tìm kiếm nhị phân (Binary Search Tree - BST)**, ...
- Ứng dụng các cấu trúc dữ liệu đã học để giải quyết bài toán thực tế: Thêm, xóa, sửa, và đặc biệt là **lọc (filter)** sản phẩm theo giá hoặc theo đánh giá.
- Sử dụng Generic Type (`<T>`) để tạo các cấu trúc dữ liệu có khả năng tái sử dụng.

## Cấu trúc mã nguồn

- `src/entity/Product.java`: Lớp đại diện cho Sản phẩm (ID, Tên, Danh mục, Giá, Đánh giá).
- `src/datastructure/list/`: Cấu trúc Danh sách liên kết do sinh viên tự xây dựng.
- `src/datastructure/tree/`: Cấu trúc Cây tìm kiếm nhị phân (BST) dùng để tối ưu việc tìm kiếm và lọc dữ liệu.
- `src/core/CatalogFilter.java`: Lớp nghiệp vụ, sử dụng các cấu trúc dữ liệu trên để quản lý `Product`.
- `src/App.java`: File chạy chính để test ứng dụng.

## Hướng dẫn chạy
Sau khi hoàn thành các phần `TODO`, bạn có thể chạy hàm `main` trong file `src/App.java` để kiểm thử kết quả.
