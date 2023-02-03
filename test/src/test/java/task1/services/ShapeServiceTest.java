package task1.services;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import task1.model.Circle;
import task1.model.Rectangle;
import task1.model.Shape;
import task1.model.Square;
import task1.utils.ObjectMapperHolder;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

public class ShapeServiceTest {

    @Mock
    private Circle circleMock1;
    @Mock
    private Circle circleMock2;
    @Mock
    private Rectangle rectangleMock1;

    private Rectangle rectangle1;
    private Rectangle rectangle2;
    private Square square1;

    private ObjectMapper objectMapper;

    private ShapeService shapeService;
    private ShapeFactory shapeFactory;


    @Before
    public void init() {
        MockitoAnnotations.openMocks(this);
        shapeService = new ShapeService();
        shapeFactory = new ShapeFactory();
        objectMapper = ObjectMapperHolder.INSTANCE.getMapper();
    }


    @Test
    public void shouldReturnShapeWithLargestArea() {
        List<Shape> shapes = List.of(circleMock1, circleMock2);

        when(circleMock1.calculateArea()).thenReturn(15.0);
        when(circleMock2.calculateArea()).thenReturn(20.0);

        assertEquals(circleMock2, shapeService.findShapeWithLargestArea(shapes));
    }

    @Test
    public void shouldReturnShapeWithLargestPerimeterAndSpecificTypeOfShape() {
        rectangle1 = shapeFactory.createRectangle(10, 20);
        rectangle2 = shapeFactory.createRectangle(10, 30);
        square1 = shapeFactory.createSquare(10);

        List<Shape> shapes = List.of(rectangle1, rectangle2, square1);

        assertEquals(rectangle2, shapeService.findShapeWithLargestPerimeter(shapes, "Rectangle"));
    }

    @Test(expected = NoSuchElementException.class)
    public void shouldReturnNoSuchElementExceptionWhenInputDataOfShapeTypeIsIncorrect() {
        List<Shape> shapes = List.of(circleMock1, rectangleMock1);

        when(circleMock1.calculatePerimeter()).thenReturn(15.0);

        when(rectangleMock1.calculatePerimeter()).thenReturn(10.0);

        shapeService.findShapeWithLargestPerimeter(shapes, "asdasd");
    }

    @Test
    public void importFromJsonTest() {
        String path = "src/main/resources/testImportFromJson.json";

        List<Shape> expected = new ArrayList<>();
        expected.add(new Square(2));

        List<Shape> actual = shapeService.importShapesFromJson(path);

        assertNotNull(actual);
        assertEquals(expected, actual);
        assertEquals(1, actual.size());
    }

    @Test
    public void exportFromJsonTest() throws IOException {
        String json = "[{\"type\":\"Rectangle\",\"length\":1.0, \"width\":1.0}]";
        String path = "src/main/resources/testExportFromJson.json";

        List<Shape> shapes = new ArrayList<>();
        shapes.add(new Rectangle(1, 1));

        shapeService.exportShapesToJson(shapes, path);

        JsonNode nodeExpected = objectMapper.readTree(json);
        JsonNode nodeActual = objectMapper.readTree(new File(path));

        assertEquals(nodeExpected, nodeActual);
    }
}