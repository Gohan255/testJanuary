package task1.services;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import task1.model.Shape;
import task1.utils.ObjectMapperHolder;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class ShapeService {

    ObjectMapper objectMapper = ObjectMapperHolder.INSTANCE.getMapper();

    public Shape findShapeWithLargestArea(List<Shape> shapes) {
        return Optional.ofNullable(shapes)
                .orElseGet(Collections::emptyList)
                .stream()
                .filter(Objects::nonNull)
                .max(Comparator.comparing(shape -> shape.calculateArea()))
                .orElseThrow();
    }

    public Shape findShapeWithLargestPerimeter(List<Shape> shapes, String type) {
        return Optional.ofNullable(shapes)
                .orElseGet(Collections::emptyList)
                .stream()
                .filter(Objects::nonNull)
                .filter(shape -> shape.getClass().getSimpleName().equalsIgnoreCase(type))
                .max(Comparator.comparing(shape -> shape.calculatePerimeter()))
                .orElseThrow();
    }

    public void exportShapesToJson(List<Shape> shapes, String path) {
        if (shapes == null) {
            throw new IllegalArgumentException();
        }
        try {
            objectMapper.writeValue(new File(path), shapes);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public List<Shape> importShapesFromJson(String path) {
        List<Shape> shapes = null;
        try {
            shapes = objectMapper.readValue(new File(path), new TypeReference<List<Shape>>() {
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
        return shapes;
    }
}
