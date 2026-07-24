package utils;

public class ValidationUtils {

    public static void validateNotNull(Object obj, String message) {
        if (obj == null) {
            throw new IllegalArgumentException(message);
        }
    }

    public static void validateStringNotEmpty(String str, String message) {
        if (str == null || str.trim().isEmpty()) {
            throw new IllegalArgumentException(message);
        }
    }

    public static void validatePositiveOrZero(double value, String message) {
        if (value < 0) {
            throw new IllegalArgumentException(message);
        }
    }

    public static void validateRating(double rating) {
        if (rating < 0 || rating > 5) {
            throw new IllegalArgumentException("Rating phải nằm trong khoảng 0 đến 5");
        }
    }

    public static void validatePriceRange(double min, double max) {
        if (min < 0 || max < 0 || min > max) {
            throw new IllegalArgumentException("Khoảng giá không hợp lệ");
        }
    }
}
