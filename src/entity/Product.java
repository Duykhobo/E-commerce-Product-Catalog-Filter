package entity;

import utils.ValidationUtils;

public class Product {
    private String id;
    private String name;
    private double price;
    private double rating;
    private boolean isActive;

    public Product(String id, String name, double price, double rating) {
        ValidationUtils.validateStringNotEmpty(id, "ID sản phẩm không hợp lệ");
        ValidationUtils.validateStringNotEmpty(name, "Tên sản phẩm không hợp lệ");
        ValidationUtils.validatePositiveOrZero(price, "Giá sản phẩm không được âm");
        ValidationUtils.validateRating(rating);
        
        this.id = id;
        this.name = name;
        this.price = price;
        this.rating = rating;
        this.isActive = true;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public double getRating() {
        return rating;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    @Override
    public String toString() {
        String status = isActive ? "" : " [ĐÃ XÓA]";
        return "Product{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", rating=" + rating +
                '}' + status;
    }
}
