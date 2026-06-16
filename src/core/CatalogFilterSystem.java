package core;

import engine.PriceEngine;
import engine.SearchEngine;
import engine.RatingSorter;
import entity.Product;
import datastructure.list.ProductLinkedList;

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
        
        // Trie và Max-Heap
        searchEngine.insertToTrie(p.getName().toLowerCase(), p);
        ratingSorter.insert(p);
    }

    // Hàm gọi chức năng lọc giá
    public ProductLinkedList filterByPrice(double min, double max) {
        ProductLinkedList result = new ProductLinkedList();
        priceEngine.searchByPriceRange(priceEngine.root, min, max, result);
        return result;
    }

    // Lấy top đánh giá cao nhất
    public ProductLinkedList getTopRated(int k) {
        ProductLinkedList topRated = new ProductLinkedList();
        for (int i = 0; i < k; i++) {
            Product p = ratingSorter.extractMax();
            if (p != null) {
                topRated.add(p);
            }
        }
        return topRated;
    }

    // Gợi ý tìm kiếm Autocomplete
    public ProductLinkedList autocomplete(String prefix) {
        return searchEngine.searchByPrefix(prefix.toLowerCase());
    }
}
