package ru.otveska.yclients.service.handler;

import org.springframework.http.HttpEntity;

public class StubResourceHandler implements ResourceHandler {

    private static final HandleResult result = new HandleResult(false);

    @Override
    public HandleResult handle(HttpEntity<String> httpEntity) {
        return result;
    }
}
