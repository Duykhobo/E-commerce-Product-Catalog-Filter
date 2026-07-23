import core.CatalogFilterSystem;
import entity.Product;
import datastructure.array.ProductArray;

public class TestPerformance {
    public static void main(String[] args) {
        int[] sizes = {100, 1000, 10000, 50000};
        
        System.out.println("=======================================================================================================================");
        System.out.printf("%-10s | %-30s | %-30s | %-30s\n", "Size (n)", "Price Filter (BST vs Linear)", "Rating Filter (Hash vs Linear)", "ID Search (Hash vs Linear)");
        System.out.printf("%-10s | %-14s | %-13s | %-14s | %-13s | %-14s | %-13s\n", "", "BST (ns)", "Linear (ns)", "Hash (ns)", "Linear (ns)", "Hash (ns)", "Linear (ns)");
        System.out.println("=======================================================================================================================");
        
        for (int n : sizes) {
            CatalogFilterSystem system = new CatalogFilterSystem(n, n);
            
            // Generate n products
            for (int i = 0; i < n; i++) {
                double rating = Math.floor(Math.random() * 5) + 1; // 1.0 to 5.0
                system.addProduct(new Product("ID" + i, "Product " + i, Math.random() * 1000, rating));
            }
            
            double minPrice = 200.0;
            double maxPrice = 500.0;
            double targetRating = 4.0;
            String targetId = "ID" + (n / 2); // Search for middle element
            
            ProductArray all = system.getAllProducts();
            
            // Warmup
            system.filterByPrice(minPrice, maxPrice);
            linearFilterPrice(all, minPrice, maxPrice);
            system.getProductsByRating(targetRating);
            linearFilterRating(all, targetRating);
            system.searchEngine.getById(targetId);
            linearSearchId(all, targetId);
            
            int iterations = 100;
            
            // 1. Measure Price Filter (BST)
            long startTime1 = System.nanoTime();
            for(int i = 0; i < iterations; i++) {
                system.filterByPrice(minPrice, maxPrice);
            }
            double avgBstTime = (System.nanoTime() - startTime1) / (double)iterations;
            
            // 2. Measure Price Filter (Linear)
            long startTime2 = System.nanoTime();
            for(int i = 0; i < iterations; i++) {
                linearFilterPrice(all, minPrice, maxPrice);
            }
            double avgLinearPriceTime = (System.nanoTime() - startTime2) / (double)iterations;
            
            // 3. Measure Rating Filter (Hash)
            long startTime3 = System.nanoTime();
            for(int i = 0; i < iterations; i++) {
                system.getProductsByRating(targetRating);
            }
            double avgHashRatingTime = (System.nanoTime() - startTime3) / (double)iterations;
            
            // 4. Measure Rating Filter (Linear)
            long startTime4 = System.nanoTime();
            for(int i = 0; i < iterations; i++) {
                linearFilterRating(all, targetRating);
            }
            double avgLinearRatingTime = (System.nanoTime() - startTime4) / (double)iterations;

            // 5. Measure ID Search (Hash)
            long startTime5 = System.nanoTime();
            for(int i = 0; i < iterations; i++) {
                system.searchEngine.getById(targetId);
            }
            double avgHashIdTime = (System.nanoTime() - startTime5) / (double)iterations;
            
            // 6. Measure ID Search (Linear)
            long startTime6 = System.nanoTime();
            for(int i = 0; i < iterations; i++) {
                linearSearchId(all, targetId);
            }
            double avgLinearIdTime = (System.nanoTime() - startTime6) / (double)iterations;
            
            System.out.printf("%-10d | %-14.4f | %-13.4f | %-14.4f | %-13.4f | %-14.4f | %-13.4f\n", 
                n, avgBstTime, avgLinearPriceTime, avgHashRatingTime, avgLinearRatingTime, avgHashIdTime, avgLinearIdTime);
        }
        System.out.println("=======================================================================================================================");
    }
    
    private static ProductArray linearFilterPrice(ProductArray all, double min, double max) {
        ProductArray result = new ProductArray();
        for (int i = 0; i < all.size; i++) {
            Product p = all.data[i];
            if (p.getPrice() >= min && p.getPrice() <= max && p.isActive()) {
                result.add(p);
            }
        }
        return result;
    }
    
    private static ProductArray linearFilterRating(ProductArray all, double rating) {
        ProductArray result = new ProductArray();
        for (int i = 0; i < all.size; i++) {
            Product p = all.data[i];
            if (p.getRating() == rating && p.isActive()) {
                result.add(p);
            }
        }
        return result;
    }

    private static Product linearSearchId(ProductArray all, String id) {
        for (int i = 0; i < all.size; i++) {
            Product p = all.data[i];
            if (p.getId().equals(id) && p.isActive()) {
                return p;
            }
        }
        return null;
    }
}
