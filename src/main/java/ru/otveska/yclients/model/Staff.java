package ru.otveska.yclients.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

/**
 *  {
 *        "id": 1142,
 *        "name": "Альфия",
 *        "spec": "парикмахер",
 *        "show_rating": "1",
 *        "rating": "0",
 *        "votes_count": "0",
 *        "avatar": "https://salonpicker.ru/images/no-master.png",
 *        "comments_count": "0"
 *   }
 */
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class Staff {

    Integer id;

    String name;

    String specialization;

    String show_rating;

    String rating;

    @JsonAlias("votes_count")
    String votesCount;

    String avatar;

    @JsonAlias("avatar_big")
    String avatarBig;

    @JsonAlias("comments_count")
    String commentsCount;
}
