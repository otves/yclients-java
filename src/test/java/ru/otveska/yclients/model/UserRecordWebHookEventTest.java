package ru.otveska.yclients.model;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.TimeZone;
import java.util.stream.Collectors;


import static org.junit.Assert.*;

public class UserRecordWebHookEventTest {

    @Test
    public void testParse() throws Exception {

        InputStream is = TypeReference.class.getResourceAsStream("/UserRecordWebHook.json");
        String json = convert(is, StandardCharsets.UTF_8);

        ObjectMapper objectMapper = new ObjectMapper().setTimeZone(TimeZone.getDefault());
        WebHookEvent event = objectMapper
                .readerFor(WebHookEvent.class)
                .readValue(json);


        switch (event.getResource()) {
            case RECORD:
                UserRecordWebHookEvent ur = objectMapper
                        .readerFor(UserRecordWebHookEvent.class)
                        .readValue(json);
                assertEquals(199630, ur.companyId.longValue());

                //2019-07-21 12:00:00
                assertEquals("21.07.19 12:00", ur.getData().getDateStr());
                SimpleDateFormat df = new SimpleDateFormat("dd.MM.yy hh:mm");
                break;
            case CLIENT:
            case SERVICE:
                //ignore yet
                break;
            default:
                break;
        }


    }

    public String convert(InputStream inputStream, Charset charset) throws IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(inputStream, charset))) {
            return br.lines().collect(Collectors.joining(System.lineSeparator()));
        }
    }
}