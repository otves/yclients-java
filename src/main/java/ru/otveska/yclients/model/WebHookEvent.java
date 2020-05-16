package ru.otveska.yclients.model;


import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class WebHookEvent {

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
     * Событие. create, update или delete
     */
    String status;

}
