package ru.otveska.yclients.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class Client {

    Integer id;

    String name;

    String phone;

    String card;

    String email;

    @JsonAlias("success_visits_count")
    String successVisitsCount;

    @JsonAlias("fail_visits_count")
    String failVisitsCount;

}
