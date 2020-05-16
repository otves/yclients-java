package ru.otveska.yclients.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum ClientRecordStatus {

    @JsonProperty("create")
    CREATE,

    @JsonProperty("update")
    UPDATE,

    @JsonProperty("delete")
    DELETE
}
