package ru.otveska.yclients.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

/**
 *  {
 *    "id": 4564,
 *    "title": "",
 *    "country_id": "0",
 *    "country": "",
 *    "city_id": "0",
 *    "city": "Москва",
 *    "phone": "+7 916 684-41-22",
 *    "timezone": "0",
 *    "address": "",
 *    "coordinate_lat": "0",
 *    "coordinate_lon": "0"
 *  }
 */
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class Company {

    Long id;

    String title;

    @JsonAlias("country_id")
    String countryId;

    String country;

    @JsonAlias("city_id")
    String city_id;

    String city;

    String phone;

    String timezone;

    String address;

    @JsonAlias("coordinate_lat")
    String coordinate_lat;

    @JsonAlias("country_id")
    String country_id;

}
