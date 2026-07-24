package engine;

import datastructure.array.ProductArray;
import datastructure.tree.TreeNode;
import entity.Product;
import utils.ValidationUtils;

public class RatingEngine {

    public TreeNode root;
    private int size;

    public static class RatingBucket {
        private double rating;
        private ProductArray products;

        public RatingBucket(double rating) {
            this.rating = rating;
            this.products = new ProductArray();
        }

        public double getRating() {
            return rating;
        }

        public ProductArray getProducts() {
            return products;
        }

        public void addProduct(Product p) {
            if (p != null) {
                products.add(p);
            }
        }
    }

    public RatingEngine() {
        this.root = null;
        this.size = 0;
    }

    public RatingEngine(int capacity) {
        this();
    }

    public boolean isEmpty() {
        return size == 0;
    }

    private int compare(double r1, double r2) {
        if (Math.abs(r1 - r2) < 1e-9) {
            return 0;
        }
        return r1 < r2 ? -1 : 1;
    }

    public void insertProduct(Product product) {
        ValidationUtils.validateNotNull(product, "Sản phẩm không được null");
        ValidationUtils.validateRating(product.getRating());

        double rating = product.getRating();

        if (root == null) {
            RatingBucket bucket = new RatingBucket(rating);
            bucket.addProduct(product);
            root = new TreeNode(bucket);
            size++;
            return;
        }

        TreeNode current = root;
        TreeNode parent = null;
        int cmp = 0;

        while (current != null) {
            parent = current;
            RatingBucket currentBucket = (RatingBucket) current.getData();
            cmp = compare(rating, currentBucket.getRating());

            if (cmp == 0) {
                currentBucket.addProduct(product);
                size++;
                return;
            } else if (cmp < 0) {
                current = current.getLeft();
            } else {
                current = current.getRight();
            }
        }

        RatingBucket newBucket = new RatingBucket(rating);
        newBucket.addProduct(product);
        TreeNode newNode = new TreeNode(newBucket);

        if (cmp < 0) {
            parent.setLeft(newNode);
        } else {
            parent.setRight(newNode);
        }
        size++;
    }

    public ProductArray getProductsByRating(double rating) {
        ValidationUtils.validateRating(rating);
        TreeNode current = root;

        while (current != null) {
            RatingBucket bucket = (RatingBucket) current.getData();
            int cmp = compare(rating, bucket.getRating());

            if (cmp == 0) {
                ProductArray filtered = new ProductArray();
                ProductArray products = bucket.getProducts();
                for (int i = 0; i < products.size; i++) {
                    Product p = products.get(i);
                    if (p != null && p.isActive()) {
                        filtered.add(p);
                    }
                }
                return filtered;
            } else if (cmp < 0) {
                current = current.getLeft();
            } else {
                current = current.getRight();
            }
        }

        return new ProductArray();
    }
}

