package ru.otveska.yclients.service;

import ru.otveska.yclients.model.Client;
import ru.otveska.yclients.model.WebHookEventsSettings;

import java.util.List;

/**
 * REST клиент для Yclients API
 */
public interface YclientsAPIService {

   WebHookEventsSettings getWebHookEventsSettings();

   WebHookEventsSettings saveWebHookEventsSettings(WebHookEventsSettings webHookEventsSettings);

    List<Client> getAllClients();

}