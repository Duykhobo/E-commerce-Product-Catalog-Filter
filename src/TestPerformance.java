import core.CatalogFilterSystem;
import entity.Product;
import datastructure.array.ProductArray;

public class TestPerformance {
    public static void main(String[] args) {
        int[] sizes = {100, 1000, 10000, 50000, 100000};
        
        System.out.println("Input Size (n)\tBST Time (ms)\tLinear Time (ms)");
        
        for (int n : sizes) {
            CatalogFilterSystem system = new CatalogFilterSystem(n, n);
            
            // Generate n products
            for (int i = 0; i < n; i++) {
                system.addProduct(new Product("ID" + i, "Product " + i, Math.random() * 1000, Math.random() * 5));
            }
            
            double minPrice = 200.0;
            double maxPrice = 500.0;
            
            // Warmup
            system.filterByPrice(minPrice, maxPrice);
            linearFilter(system.getAllProducts(), minPrice, maxPrice);
            
            // Measure BST
            long startTime1 = System.nanoTime();
            for(int i=0; i<100; i++) {
                system.filterByPrice(minPrice, maxPrice);
            }
            long endTime1 = System.nanoTime();
            double avgBstTime = (endTime1 - startTime1) / 100.0 / 1_000_000.0;
            
            // Measure Linear
            ProductArray all = system.getAllProducts();
            long startTime2 = System.nanoTime();
            for(int i=0; i<100; i++) {
                linearFilter(all, minPrice, maxPrice);
            }
            long endTime2 = System.nanoTime();
            double avgLinearTime = (endTime2 - startTime2) / 100.0 / 1_000_000.0;
            
            System.out.printf("%d\t\t%.4f\t\t%.4f\n", n, avgBstTime, avgLinearTime);
        }
    }
    
    private static ProductArray linearFilter(ProductArray all, double min, double max) {
        ProductArray result = new ProductArray();
        for (int i = 0; i < all.size; i++) {
            Product p = all.data[i];
            if (p.getPrice() >= min && p.getPrice() <= max && p.isActive()) {
                result.add(p);
            }
        }
        return result;
    }
}
