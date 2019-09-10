package br.com.ryoshino.controller;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.util.Map;

public final class DefaultMapperUtils {

    private static final ObjectMapper MAPPER = new ObjectMapper();

    private static final JavaType mapType;

    static {
        MAPPER.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, true);
        MAPPER.configure(SerializationFeature.WRITE_ENUMS_USING_TO_STRING, true);
        MAPPER.configure(DeserializationFeature.READ_ENUMS_USING_TO_STRING, true);
        MAPPER.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        mapType = MAPPER.getTypeFactory().constructParametricType(Map.class, String.class, Object.class);
    }

    private DefaultMapperUtils() {
        super();
    }

    public static ObjectMapper getDefaultObjectMapper() {
        return MAPPER;
    }

    public static Map<String, Object> convertToMap(Object response) {
        if (response == null) {
            return null;
        }
        return MAPPER.convertValue(response, mapType);
    }

}
