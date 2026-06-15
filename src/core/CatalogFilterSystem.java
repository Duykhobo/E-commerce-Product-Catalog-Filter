package core;

import engine.PriceEngine;
import engine.SearchEngine;
import entity.Product;
import java.util.ArrayList;
import java.util.List;

public class CatalogFilterSystem {
    public PriceEngine priceEngine;
    public SearchEngine searchEngine;

    public CatalogFilterSystem(int hashCapacity) {
        this.priceEngine = new PriceEngine();
        this.searchEngine = new SearchEngine(hashCapacity);
    }

    // TODO (Toàn nhóm): Triển khai thêm Product vào toàn bộ hệ thống
    public void addProduct(Product p) {
        // Gọi hàm của 2 engine để thêm dữ liệu đồng thời
        priceEngine.insertProduct(p);
        searchEngine.put(p.getId(), p);
    }

    // Hàm gọi chức năng lọc giá
    public List<Product> filterByPrice(double min, double max) {
        List<Product> result = new ArrayList<>();
        priceEngine.searchByPriceRange(priceEngine.root, min, max, result);
        return result;
    }
}
