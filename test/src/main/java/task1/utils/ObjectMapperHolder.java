package task1.utils;

import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import task1.serializers.ShapeDeserializer;
import task1.serializers.ShapeSerializer;
import task1.model.Shape;

public enum ObjectMapperHolder {

    INSTANCE;

    private final ObjectMapper mapper;

    ObjectMapperHolder() {
        this.mapper = create();
    }

    private static ObjectMapper create() {
        ObjectMapper mapper = new ObjectMapper();

        ShapeSerializer ss = new ShapeSerializer(Shape.class);
        SimpleModule sm1 = new SimpleModule("ShapeSerialzier",
                new Version(1, 0, 0, null, null, null));

        sm1.addSerializer(Shape.class, ss);
        mapper.registerModule(sm1);

        ShapeDeserializer sd = new ShapeDeserializer(Shape.class);
        SimpleModule sm2 = new SimpleModule("ShapeDeserializer",
                new Version(1, 0, 0, null, null, null));

        sm2.addDeserializer(Shape.class, sd);
        mapper.registerModule(sm2);


        return mapper;
    }

    public ObjectMapper getMapper() {
        return mapper;
    }
}

