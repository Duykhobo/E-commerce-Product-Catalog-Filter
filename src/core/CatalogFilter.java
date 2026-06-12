package core;

import datastructure.list.MyLinkedList;
import datastructure.tree.MyBST;
import entity.Product;

public class CatalogFilter {
    // Không dùng thư viện có sẵn, tự dùng MyLinkedList, MyBST đã định nghĩa
    private MyLinkedList<Product> productList;
    private MyBST<Product> productTreeByPrice; 

    public CatalogFilter() {
        productList = new MyLinkedList<>();
        productTreeByPrice = new MyBST<>();
    }

    // TODO: Triển khai thêm Product vào các cấu trúc dữ liệu
    
    public void addProduct(Product product) {
        // productList.addLast(product);
        // productTreeByPrice.insert(product);
    }

    // TODO: Triển khai lọc sản phẩm theo mức giá (Dùng BST để tối ưu)
    public void filterByPrice(double minPrice, double maxPrice) {
        // ...
    }

    // TODO: Triển khai hiển thị toàn bộ sản phẩm (Dùng Linked List)
    public void displayAllProducts() {
        // productList.display();
    }
}
