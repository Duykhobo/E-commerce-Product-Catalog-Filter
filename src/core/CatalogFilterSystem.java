package core;

import engine.PriceEngine;
import engine.SearchEngine;
import engine.RatingSorter;
import entity.Product;
import datastructure.array.ProductArray;

public class CatalogFilterSystem {
    public PriceEngine priceEngine;
    public SearchEngine searchEngine;
    public RatingSorter ratingSorter;

    public CatalogFilterSystem(int hashCapacity, int heapCapacity) {
        this.priceEngine = new PriceEngine();
        this.searchEngine = new SearchEngine(hashCapacity);
        this.ratingSorter = new RatingSorter(heapCapacity);
    }

    // TODO (Toàn nhóm): Triển khai thêm Product vào toàn bộ hệ thống
    public void addProduct(Product p) {
        // Gọi hàm của các engine để thêm dữ liệu đồng thời
        priceEngine.insertProduct(p);
        searchEngine.put(p.getId(), p);
        
        // Mảng động và Mảng sắp xếp sẵn
        searchEngine.insertToArray(p.getName().toLowerCase(), p);
        ratingSorter.insert(p);
    }

    // Hàm gọi chức năng lọc giá
    public ProductArray filterByPrice(double min, double max) {
        ProductArray result = new ProductArray();
        priceEngine.searchByPriceRange(priceEngine.root, min, max, result);
        return result;
    }

    // Lấy top đánh giá cao nhất
    public ProductArray getTopRated(int k) {
        return ratingSorter.getTopRated(k);
    }

    // Gợi ý tìm kiếm Autocomplete
    public ProductArray autocomplete(String prefix) {
        return searchEngine.searchByPrefix(prefix.toLowerCase());
    }

    // Lấy toàn bộ sản phẩm (Read)
    public ProductArray getAllProducts() {
        ProductArray result = new ProductArray();
        for (int i = 0; i < searchEngine.productArray.size; i++) {
            Product p = searchEngine.productArray.data[i];
            if (p.isActive()) {
                result.add(p);
            }
        }
        return result;
    }

    // Cập nhật sản phẩm (Update)
    public boolean updateProduct(String id, String newName, double newPrice, double newRating) {
        Product oldProduct = searchEngine.getById(id);
        if (oldProduct == null) {
            return false;
        }
        oldProduct.setActive(false); // Xóa mềm cái cũ
        Product newProduct = new Product(id, newName, newPrice, newRating);
        addProduct(newProduct); // Thêm cái mới
        return true;
    }

    // Xóa sản phẩm (Delete)
    public boolean deleteProduct(String id) {
        Product p = searchEngine.getById(id);
        if (p != null) {
            p.setActive(false);
            return true;
        }
        return false;
    }
}
