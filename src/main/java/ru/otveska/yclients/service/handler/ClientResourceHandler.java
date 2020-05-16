package ru.otveska.yclients.service.handler;

import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Component;

@Component("CLIENT")
public class ClientResourceHandler implements ResourceHandler {

    @Override
    public HandleResult handle(HttpEntity<String> httpEntity) {
        //TODO:
        return new HandleResult(false);
    }

}
