package ru.otveska.yclients.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import ru.otveska.yclients.YClientsConst;
import lombok.Getter;
import lombok.Setter;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * {
 * "id": 30358,
 * "services": [
 * {
 * "id": 2838,
 * "title": "Массаж ног",
 * "cost": 0,
 * "discount": 0
 * }
 * ],
 * "company": {
 * "id": 4564,
 * "title": "",
 * "country_id": "0",
 * "country": "",
 * "city_id": "0",
 * "city": "Москва",
 * "phone": "+7 916 684-41-22",
 * "timezone": "0",
 * "address": "",
 * "coordinate_lat": "0",
 * "coordinate_lon": "0"
 * },
 * "staff": {
 * "id": 924,
 * "name": "Евгения",
 * "spec": "о ес",
 * "show_rating": "1",
 * "rating": "5",
 * "votes_count": "1",
 * "avatar": "https://salonpicker.ru/images/no-master.png",
 * "comments_count": "0"
 * },
 * "date": "2012-04-02 12:00:00",
 * "create_date": "0000-00-00 00:00:00",
 * "comment": "",
 * "deleted": true,
 * "length": 3600,
 * "notify_by_sms": 0,
 * "notify_by_email": 0,
 * "master_requested": false,
 * "online": true,
 * "api_id": "0"
 * }
 */
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class ClientRecord {

    Long id;

    List<Service> services;

    @JsonAlias("company_id")
    String companyId;

    @JsonAlias("staff_id")
    String staffId;

    Company company;

    Client client;

    Staff staff;

    /**
     * Дата сеанса.
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = YClientsConst.DATE_FORMAT)
    Date date;

    /**
     * Дата создания записию.
     */
    @JsonAlias("create_date")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = YClientsConst.DATE_FORMAT2)
    Date createDate;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = YClientsConst.DATE_FORMAT3)
    Date datetime;

    @JsonAlias("last_change_date")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = YClientsConst.DATE_FORMAT2)
    Date lastChangeDate;

    String comment;

    Boolean deleted;

    Boolean prepaid;

    @JsonAlias("prepaid_confirmed")
    Boolean prepaidConfirmed;

    Integer length;

    @JsonAlias("activity_id")
    Long activityId;

    @JsonAlias("seance_length")
    Long seanceLength;

    @JsonAlias("paid_full")
    Long paidFull;

    @JsonAlias("sms_before")
    Integer smsBefore;

    @JsonAlias("sms_now")
    Integer smsNow;

    @JsonAlias("email_now")
    Integer emailNow;

    @JsonAlias("sms_now_text")
    Integer smsNowText;

    Integer notified;

    @JsonAlias("notify_by_sms")
    Boolean notifyBySms;

    @JsonAlias("visit_attendance")
    Integer visitAttendance;

    Integer attendance;

    @JsonAlias("visit_id")
    Long visitId;

    @JsonAlias("created_user_id")
    Long createdUserId;

    Integer confirmed;

    @JsonAlias("clients_count")
    Boolean clientsCount;

    @JsonAlias("notify_by_email")
    Boolean notifyByEmail;

    @JsonAlias("master_request")
    Boolean masterRequest;

    @JsonAlias("review_requested")
    Boolean reviewRequested;

    Boolean online;

    @JsonAlias("api_id")
    String apiId;

    @JsonAlias("from_url")
    String fromUrl;

    List<Document> documents;

    String getDateStr() {
        SimpleDateFormat df = new SimpleDateFormat("dd.MM.yy HH:mm");
        return df.format(date);
    }

}
