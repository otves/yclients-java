package ru.otveska.yclients.service.handler;

import lombok.Data;

@Data
public class HandleResult {

    private final boolean succcess;

    HandleResult(boolean succcess) {
        this.succcess = succcess;
    }

}
