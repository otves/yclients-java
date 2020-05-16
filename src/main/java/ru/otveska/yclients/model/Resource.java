package ru.otveska.yclients.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum Resource {

    @JsonProperty("company")
    COMPANY,

    @JsonProperty("service_category")
    SERVICE_CATEGORY,

    @JsonProperty("service")
    SERVICE,

    @JsonProperty("staff")
    STAFF,

    @JsonProperty("client")
    CLIENT,

    @JsonProperty("record")
    RECORD

}
