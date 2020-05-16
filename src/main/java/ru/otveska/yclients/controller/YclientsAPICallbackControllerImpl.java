package ru.otveska.yclients.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import ru.otveska.yclients.service.ResourceHandlerFactory;
import ru.otveska.yclients.service.YclientsAPIService;
import ru.otveska.yclients.conifg.AppConfig;
import ru.otveska.yclients.model.WebHookEvent;
import ru.otveska.yclients.model.WebHookEventsSettings;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.otveska.yclients.service.handler.HandleResult;
import ru.otveska.yclients.util.YClientsAPIUtil;

import javax.annotation.PostConstruct;
import java.io.IOException;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/callback")
public class YclientsAPICallbackControllerImpl implements YclientsAPICallbackController {

    private static final Logger logger = LoggerFactory.getLogger(YclientsAPICallbackControllerImpl.class);

    private final YclientsAPIService yclientsAPIService;
    private final AppConfig appConfig;
    private final ResourceHandlerFactory resourceHandlerFactory;

    @Autowired
    public YclientsAPICallbackControllerImpl(YclientsAPIService yclientsAPIService,
                                             AppConfig appConfig,
                                             ResourceHandlerFactory resourceHandlerFactory) {
        this.yclientsAPIService = yclientsAPIService;
        this.appConfig = appConfig;
        this.resourceHandlerFactory = resourceHandlerFactory;
    }

    @GetMapping("/test")
    @ResponseBody
    public String test() {
        return "OK";
    }

    /**
     * Точка входя для Callback от Yclients
     *
     * @param httpEntity
     * @return
     */
    @PostMapping
    @ResponseBody
    public ResponseEntity callBackPost(HttpEntity<String> httpEntity) {
        logger.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        logger.info(httpEntity.getBody());
        try {
            WebHookEvent event = YClientsAPIUtil.readValue(httpEntity, WebHookEvent.class);
            logger.info("RESOURCE: {}", event.getResource());
            HandleResult result = resourceHandlerFactory.getResourceHandler(event.getResource()).handle(httpEntity);
            logger.debug("Result: {}" , result.isSucccess());
        } catch (IOException e) {
            logger.error("", e);
        }
        logger.info("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
        return new ResponseEntity(OK);
    }

    @PostConstruct
    void init() {
        saveWebHookEvents();
    }

    private void saveWebHookEvents() {
        WebHookEventsSettings settings = WebHookEventsSettings.builder()
                .url(appConfig.getCallbackUrl())
                .active(1)
                .salon(1)
                .serviceCategory(1)
                .good(1)
                .client(1)
                .record(1)
                .financesOperation(1)
                .goodsOperationsConsumable(1)
                .goodsOperationsReceipt(1)
                .goodsOperationsStolen(1)
                .goodsOperationsSale(1)
                .goodsOperationsMove(1)
                .master(1)
                .build();
        yclientsAPIService.saveWebHookEventsSettings(settings);
    }

}
