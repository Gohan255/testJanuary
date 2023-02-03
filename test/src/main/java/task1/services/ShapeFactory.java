package task1.services;

import task1.exceptions.InvalidInputException;
import task1.model.Circle;
import task1.model.Rectangle;
import task1.model.Shape;
import task1.model.Square;

import java.util.HashMap;
import java.util.Map;

public class ShapeFactory {

    private final Map<String, Shape> cache = new HashMap<>();

    public Square createSquare(double length) {
        if (length <= 0) {
            throw new InvalidInputException("Value must be greater than 0!");
        }
        String key = "square_" + length;
        if (cache.containsKey(key)) {
            return (Square) cache.get(key);
        }
        Square square = new Square(length);
        cache.put(key, square);
        return square;
    }

    public Circle createCircle(double radius) {
        if (radius <= 0) {
            throw new InvalidInputException("Value must be greater than 0!");
        }
        String key = "circle_" + radius;
        if (cache.containsKey(key)) {
            return (Circle) cache.get(key);
        }
        Circle circle = new Circle(radius);
        cache.put(key, circle);
        return circle;
    }

    public Rectangle createRectangle(double width, double height) {
        if (width <= 0 || height <= 0) {
            throw new InvalidInputException("Both values must be greater than 0!");
        }
        String key = "rectangle_" + width + "_" + height;
        if (cache.containsKey(key)) {
            return (Rectangle) cache.get(key);
        }
        Shape rectangle = new Rectangle(width, height);
        cache.put(key, rectangle);
        return (Rectangle) rectangle;
    }

    public void clearCache() {
        cache.clear();
    }
}
