package task1.serializers;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import task1.model.Shape;
import task1.services.ShapeFactory;

import java.io.IOException;

public class ShapeDeserializer extends StdDeserializer<Shape> {

    private ShapeFactory shapeFactory;

    public ShapeDeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public Shape deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JacksonException {
        shapeFactory = new ShapeFactory();

        JsonNode jsonNode = jsonParser.getCodec().readTree(jsonParser);

        String type = jsonNode.get("type").asText();
        if (type.equalsIgnoreCase("circle")) {
            double radius = jsonNode.get("radius").asDouble();
            return shapeFactory.createCircle(radius);
        }
        if (type.equalsIgnoreCase("square")) {
            double side = jsonNode.get("length").asDouble();
            return shapeFactory.createSquare(side);
        }
        if (type.equalsIgnoreCase("rectangle")) {
            double height = jsonNode.get("width").asDouble();
            double length = jsonNode.get("length").asDouble();

            return shapeFactory.createRectangle(height, length);
        }
        return null;
    }
}
