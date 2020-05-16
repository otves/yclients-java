package ru.otveska.yclients.model;


import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class UserRecordWebHookEvent implements IWebHookEvent {

    /**
     * ID компании в рамках которой происходит событие
     */
    @JsonAlias("company_id")
    Long companyId;
    /**
     * Имя сущности. Одно из: company, service_category, service, staff, client, record
     */
    Resource resource;
    /**
     * ID сущности в YCLIENTS
     */
    @JsonAlias("resource_id")
    Long resourceId;
    /**
     * Событие.
     */
    ClientRecordStatus status;

    /**
     * Параметры сущности. Используется тот же набор полей что и при обращении к сущности через API.
     */
    ClientRecord data;

}
