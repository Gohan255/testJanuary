package task1.serializers;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import task1.model.Circle;
import task1.model.Rectangle;
import task1.model.Shape;
import task1.model.Square;

import java.io.IOException;

public class ShapeSerializer extends StdSerializer<Shape> {

    public ShapeSerializer(Class<Shape> t) {
        super(t);
    }

    @Override
    public void serialize(Shape shape, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {

        String type = shape.getClass().getSimpleName();

        if (type.equalsIgnoreCase("rectangle")) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeObjectField("type", shape.getClass().getSimpleName());
            jsonGenerator.writeObjectField("length", ((Rectangle) shape).getLength());
            jsonGenerator.writeObjectField("width", ((Rectangle) shape).getWidth());
            jsonGenerator.writeEndObject();
        }
        if (type.equalsIgnoreCase("square")) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeObjectField("type", shape.getClass().getSimpleName());
            jsonGenerator.writeObjectField("length", ((Square) shape).getLength());
            jsonGenerator.writeEndObject();
        }
        if (type.equalsIgnoreCase("circle")) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeObjectField("type", shape.getClass().getSimpleName());
            jsonGenerator.writeObjectField("radius", ((Circle) shape).getRadius());
            jsonGenerator.writeEndObject();
        }
    }
}
