package ru.otveska.yclients.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpEntity;

import java.io.IOException;
import java.util.TimeZone;

public class YClientsAPIUtil {

    private static final ObjectMapper objectMapper = new ObjectMapper().setTimeZone(TimeZone.getDefault());

    public static ObjectMapper objectMapper() {
        return objectMapper;
    }

    public static <T> T readValue(HttpEntity<String> httpEntity, Class<T> valueClass) throws IOException {
        return objectMapper.readerFor(valueClass).readValue(httpEntity.getBody());
    }

}
