package task1.services;

import org.junit.Before;
import org.junit.Test;
import task1.exceptions.InvalidInputException;
import task1.model.Circle;
import task1.model.Rectangle;
import task1.model.Shape;
import task1.model.Square;

import java.security.spec.DSAPrivateKeySpec;
import java.util.List;

import static org.junit.Assert.*;

public class ShapeFactoryTest {

    private ShapeFactory shapeFactory;
    private Square square1;
    private Square square2;
    private Rectangle rectangle;
    private Circle circle;

    @Before
    public void init() {
        shapeFactory = new ShapeFactory();
    }

    @Test
    public void shouldReturnTrueWhenCreateNewTheSameSquares() {
        square1 = shapeFactory.createSquare(10);
        square2 = shapeFactory.createSquare(10);

        assertTrue(square1 == square2);
    }

    @Test
    public void shouldReturnFalseWhenCreateNewDifferentSquares() {
        square1 = shapeFactory.createSquare(10);
        square2 = shapeFactory.createSquare(15);

        assertFalse(square1 == square2);
    }

    @Test
    public void shouldReturnFalseCacheMiss() {
        square1 = shapeFactory.createSquare(10);
        shapeFactory.clearCache();
        square2 = shapeFactory.createSquare(10);

        assertFalse(square1 == square2);
    }

    @Test
    public void shouldNotReturnNullWhenCreatingEveryOtherObjectWithFabricMethods() {
        square1 = shapeFactory.createSquare(10);
        rectangle = shapeFactory.createRectangle(10, 5);
        circle = shapeFactory.createCircle(10);

        assertFalse(square1 == null);
        assertFalse(rectangle == null);
        assertFalse(circle == null);

    }

    @Test(expected = InvalidInputException.class)
    public void testInvalidInputException() {
        square1 = shapeFactory.createSquare(-10);
    }
}