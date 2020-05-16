package ru.otveska.yclients.service;

import ru.otveska.yclients.conifg.YclientsConfig;
import ru.otveska.yclients.model.Client;
import ru.otveska.yclients.model.WebHookEventsSettings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.*;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * REST клиент для Yclients API
 */
@Service
public class YclientsAPIServiceImpl implements YclientsAPIService {

    private static final Logger logger = LoggerFactory.getLogger(YclientsAPIServiceImpl.class);

    private final YclientsConfig config;

    private final WebClient webClient;

    private String adminAuthToken;

    @Autowired
    public YclientsAPIServiceImpl(YclientsConfig config) {
        this.config = config;
        ExchangeFilterFunction printlnFilter = (request, next) -> {
            logger.info("\n\n" + request.method().toString().toUpperCase() +
                    ":\n\nURL:" + request.url().toString() +
                    ":\n\nHeaders:" + request.headers().toString() +
                    "\n\nAttributes:" + request.attributes() + "\n\n");
            return next.exchange(request);
        };

        this.webClient = WebClient
                .builder()
                .baseUrl(config.getApiUrl())
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .filter(printlnFilter)
                .build();

        authByAdmin();
    }

    @Override
    public WebHookEventsSettings getWebHookEventsSettings() {
        logger.info("getWebHookEventsSettings()");
        WebHookEventsSettings res = webClient().get().uri("/hooks_settings/{company_id}", config.getCompanyId())
                .header("Authorization", authHeader())
                .exchange()
                .flatMap(clientResponse -> {
                    if (clientResponse.statusCode() == HttpStatus.NOT_FOUND) {
                        return Mono.empty();
                    }
                    return clientResponse.bodyToMono(WebHookEventsSettings.class);
                })
                .block(config.getTimeout());
        ;
        logger.info("getWebHookEventsSettings: {}", res);
        return res;
    }

    @Override
    public WebHookEventsSettings saveWebHookEventsSettings(WebHookEventsSettings webHookEventsSettings) {
        logger.info("saveWebHookEventsSettings: {}", webHookEventsSettings);
        WebHookEventsSettings res = webClient().post().uri("/hooks_settings/{company_id}", config.getCompanyId())
                .header("Authorization", authHeader())
                .body(BodyInserters.fromObject(webHookEventsSettings)).retrieve()
                .bodyToMono(WebHookEventsSettings.class).block(config.getTimeout());
        logger.info("saveWebHookEventsSettings res: {}", res);
        return res;
    }

    public List<Client> getAllClients() {
        logger.info("getAllClients()");
        Object res = webClient().get().uri("/client/{company_id}", config.getCompanyId())
                .header("Authorization", authHeader())
                .exchange()
                .flatMap(clientResponse -> {
                    if (clientResponse.statusCode() == HttpStatus.NOT_FOUND) {
                        return Mono.empty();
                    }
                    return clientResponse.bodyToMono(Object.class);
                })
                .block(config.getTimeout());
        logger.info("getWebHookEventsSettings: {}", res);
        return new ArrayList();
    }

    private String authHeader() {
        return "Bearer " + config.getPartnerToken() + ", User  " + adminAuthToken;
    }

    private void authByAdmin() {
        logger.info("authByAdmin");
        Map<String, String> requestData = new HashMap<>(2);
        requestData.put("login", config.getAdminLogin());
        requestData.put("password", config.getAdminPassword());
        Map res = webClient().post().uri("/auth").body(BodyInserters.fromObject(requestData))
                .header("Authorization", "Bearer " + config.getPartnerToken())
                .retrieve().bodyToMono(Map.class).block(config.getTimeout());
        adminAuthToken = (String) res.get("user_token");
        logger.info("authByAdmin adminAuthToken: {}", adminAuthToken);
    }

    private WebClient webClient() {
        return webClient;
    }

}
