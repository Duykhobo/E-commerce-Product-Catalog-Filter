package core;

import engine.PriceEngine;
import engine.SearchEngine;
import engine.RatingEngine;
import engine.SearchHistory;
import engine.ShoppingCart;
import entity.Product;
import datastructure.array.ProductArray;

public class CatalogFilterSystem {
    public PriceEngine priceEngine;
    public SearchEngine searchEngine;
    public RatingEngine ratingEngine;
    public SearchHistory searchHistory;
    public ShoppingCart shoppingCart;

    public CatalogFilterSystem(int hashCapacity, int heapCapacity) {
        this.priceEngine = new PriceEngine();
        this.searchEngine = new SearchEngine(hashCapacity);
        this.ratingEngine = new RatingEngine(hashCapacity);
        this.searchHistory = new SearchHistory(5);
        this.shoppingCart = new ShoppingCart();
    }

    public void addProduct(Product p) {
        priceEngine.insertProduct(p);
        searchEngine.put(p.getId(), p);
        searchEngine.insertToArray(p.getName().toLowerCase(), p);
        ratingEngine.insertProduct(p);
    }

    public ProductArray filterByPrice(double min, double max) {
        ProductArray result = new ProductArray();
        priceEngine.searchByPriceRange(priceEngine.root, min, max, result);
        return result;
    }

    public ProductArray getProductsByRating(double rating) {
        return ratingEngine.getProductsByRating(rating);
    }

    public ProductArray autocomplete(String prefix) {
        searchHistory.addSearchTerm(prefix);
        return searchEngine.searchByPrefix(prefix.toLowerCase());
    }

    public ProductArray getAllProducts() {
        ProductArray result = new ProductArray();
        for (int i = 0; i < searchEngine.productArray.size; i++) {
            Product p = searchEngine.productArray.get(i);
            if (p != null && p.isActive()) {
                result.add(p);
            }
        }
        return result;
    }

    public boolean updateProduct(String id, String newName, double newPrice, double newRating) {
        Product oldProduct = searchEngine.getById(id);
        if (oldProduct == null) {
            return false;
        }
        oldProduct.setActive(false); 
        Product newProduct = new Product(id, newName, newPrice, newRating);
        addProduct(newProduct); 
        return true;
    }

    public boolean deleteProduct(String id) {
        Product p = searchEngine.getById(id);
        if (p != null) {
            p.setActive(false);
            return true;
        }
        return false;
    }
}
