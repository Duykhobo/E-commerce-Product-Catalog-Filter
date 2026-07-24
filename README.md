# 🛒 E-Commerce Product Catalog System

Đồ án Môn học: **Cấu trúc Dữ liệu và Giải thuật (CSD201) - Đại học FPT**

Một hệ thống quản lý danh mục sản phẩm và lõi tìm kiếm (Backend Search Engine) cho trang thương mại điện tử, được viết hoàn toàn bằng **Java Console**. Dự án này không dùng đến Database (SQL), thay vào đó ứng dụng linh hoạt các **Cấu trúc Dữ liệu thuật toán cốt lõi** để tối ưu hóa hiệu năng truy xuất lên mức tối đa.

## 🌟 Chức năng nổi bật

- **Quản lý Sản phẩm (CRUD):** Thêm, Xem (Có phân trang), Cập nhật, và Xóa sản phẩm.
- **Xóa Mềm (Soft Delete):** Sản phẩm xóa sẽ bị ẩn đi thay vì mất vĩnh viễn, kèm theo chức năng Khôi phục (Restore) khi cần thiết.
- **Lưu trữ Persistent:** Dữ liệu sản phẩm được tự động đọc và ghi đồng bộ xuống file `data.csv`.
- **Tìm kiếm Thần tốc (ID Search):** Tra cứu thông tin sản phẩm tức thì không độ trễ thông qua *Hash Table*.
- **Tìm kiếm Tiền tố (Autocomplete):** Hỗ trợ gõ vài ký tự đầu để gợi ý tên sản phẩm, giống thanh tìm kiếm Google.
- **Lọc Khoảng Giá (Price Filter):** Sử dụng Cây nhị phân (*Binary Search Tree*) để thu hẹp khoảng giá sản phẩm một cách thông minh.
- **Giỏ Hàng (Shopping Cart):** Quản lý Session bằng *Linked List*.
- **Lịch sử tìm kiếm:** Ghi nhớ 5 từ khóa tìm kiếm gần nhất bằng *Circular Queue*.
- **Giao diện Terminal UX cao:** Danh sách dữ liệu bọc trong Bảng ASCII đẹp mắt, có hệ thống Phân trang (Pagination) thông minh chống trôi màn hình, đi kèm cơ chế bắt lỗi Validation chống sập chương trình.

---

## 🛠️ Cấu trúc dữ liệu đã áp dụng (Data Structures)

Sức mạnh của dự án nằm ở việc mỗi tính năng đều được gắn cho một cấu trúc dữ liệu sinh ra dành riêng cho nó:

1. **Hash Table (Bảng Băm):** 
   - Ứng dụng: Tìm kiếm bằng ID (SearchEngine) & Lọc theo Rating (RatingEngine).
   - Độ phức tạp: *O(1)*.
2. **Binary Search Tree (Cây Nhị Phân Tìm Kiếm):**
   - Ứng dụng: Lọc sản phẩm nằm trong khoảng Giá `[min, max]`. Tự động cắt tỉa (Pruning) các nhánh vượt ngoài điều kiện.
   - Độ phức tạp: *O(log N)*.
3. **Dynamic Array (Mảng Động):**
   - Ứng dụng: Lưu trữ Master Data để hiển thị Bảng, và thực hiện duyệt Tìm kiếm tiền tố (Autocomplete).
   - Độ phức tạp: *O(N)*.
4. **Singly Linked List (Danh sách liên kết đơn):**
   - Ứng dụng: Giỏ hàng (Shopping Cart), tự do co giãn số lượng mua.
5. **Circular Queue (Hàng đợi vòng):**
   - Ứng dụng: Lưu Lịch sử 5 từ khóa tìm kiếm gần nhất. Đảm bảo dung lượng RAM luôn là hằng số.

*(Xem chi tiết phân tích thuật toán tại file `DATA_STRUCTURES.md`)*

---

## 📁 Cấu trúc thư mục (Project Structure)

```text
src/
 ┣ core/
 ┃ ┗ CatalogFilterSystem.java  # Lớp Orchestrator quản lý toàn bộ các Engine
 ┣ datastructure/              # Nơi tự build các cấu trúc dữ liệu từ đầu
 ┃ ┣ array/ProductArray.java
 ┃ ┣ hash/HashNode.java
 ┃ ┣ linkedlist/CartNode.java
 ┃ ┗ tree/TreeNode.java
 ┣ engine/                     # Các thuật toán xử lý nghiệp vụ chính
 ┃ ┣ PriceEngine.java          # BST
 ┃ ┣ RatingEngine.java         # Hash Table
 ┃ ┣ SearchEngine.java         # Hash Table & Array
 ┃ ┣ SearchHistory.java        # Queue
 ┃ ┗ ShoppingCart.java         # Linked List
 ┣ entity/
 ┃ ┗ Product.java              # Model Đối tượng Sản phẩm
 ┣ utils/
 ┃ ┣ FileUtils.java            # Đọc / Ghi file CSV
 ┃ ┗ ValidationUtils.java      # Check ràng buộc đầu vào
 ┣ App.java                    # Entry point (Main) & UI Terminal
 ┗ TestPerformance.java        # Script chạy test hiệu năng
```

---

## 🚀 Hướng dẫn chạy chương trình

### Yêu cầu hệ thống:
- Java JDK 8 trở lên.
- Giao diện chạy tốt nhất trên Windows Terminal hoặc CMD/PowerShell.

### Cách chạy:
1. Compile toàn bộ mã nguồn:
   ```bash
   javac -d bin src/**/*.java src/*.java
   ```
2. Chạy ứng dụng:
   ```bash
   java -cp bin App
   ```

*(File `data.csv` sẽ được hệ thống tự động sinh ra và quản lý tại thư mục gốc của Project).*

---
*Developed for CSD201 - Data Structures and Algorithms.*
