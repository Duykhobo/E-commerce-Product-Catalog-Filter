package engine;

import datastructure.array.ProductArray;
import entity.Product;

public class RatingSorter {
    public ProductArray topRatingArray;
    public int capacity;

    public RatingSorter(int capacity) {
        this.capacity = capacity;
        this.topRatingArray = new ProductArray();
    }

    // TODO (Trịnh Lê Thiên Quân): Viết hàm thêm sản phẩm vào mảng và thực hiện
    // chèn có sắp xếp (Insertion Sort) dựa theo Rating giảm dần
    public void insert(Product product) {
        if (product == null) {
            return;
        }
        // Thêm vào cuối mảng
        topRatingArray.add(product);
        
        // Thực hiện Insertion Sort dựa theo Rating giảm dần
        int i = topRatingArray.size - 1;
        while (i > 0 && topRatingArray.data[i].getRating() > topRatingArray.data[i - 1].getRating()) {
            Product tmp = topRatingArray.data[i];
            topRatingArray.data[i] = topRatingArray.data[i - 1];
            topRatingArray.data[i - 1] = tmp;
            i--;
        }
    }

    // TODO (Nguyễn Thanh Duy): Viết hàm trích xuất mảng chứa K sản phẩm có Rating
    // cao nhất
    public ProductArray getTopRated(int k) {
        ProductArray result = new ProductArray();

        int count = 0;
        for (int i = 0; i < topRatingArray.size; i++) {
            Product p = topRatingArray.data[i];
            if (!p.isActive()) {
                continue;
            }
            result.add(p);
            count++;

            if (count == k) {
                break;
            }
        }
        return result;
    }
}
