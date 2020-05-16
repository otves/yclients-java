package ru.otveska.yclients.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

/**
 *  {
 *   "id": 2838,
 *   "title": "Массаж ног",
 *   "cost": 0,
 *   "discount": 0
 *  }
 */
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class Service {

    Long id;

    String title;

    Integer cost;

    Integer discount;

    @JsonAlias("manual_cost")
    Integer manualCost;

    @JsonAlias("cost_per_unit")
    Integer costPerUnit;

    @JsonAlias("first_cost")
    Integer firstCost;

    Integer amount;

}
