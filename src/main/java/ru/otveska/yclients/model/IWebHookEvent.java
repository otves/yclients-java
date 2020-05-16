package ru.otveska.yclients.model;


interface IWebHookEvent {

    /**
     * ID компании в рамках которой происходит событие
     */
    Long getCompanyId();

    /**
     * Имя сущности. Одно из: company, service_category, service, staff, client, record
     */
    Resource getResource();

    /**
     * ID сущности в YCLIENTS
     */
    Long getResourceId();

    /**
     * Событие. create, update или delete @ClientRecordStatus
     */
    ClientRecordStatus getStatus();

    /**
     * Параметры сущности. Используется тот же набор полей что и при обращении к сущности через API.
     */
    Object getData();

}