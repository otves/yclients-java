package ru.otveska.yclients.service;

import org.springframework.http.HttpEntity;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriUtils;
import ru.otveska.yclients.conifg.AppConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.ExchangeFilterFunction;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;


/**
 * REST клиент для Sms Server'а
 * Пока минимальный функционал
 */
@Service
public class SmsService {

    private static final Logger logger = LoggerFactory.getLogger(SmsService.class);

    private AppConfig config;
    private RestTemplate restTemplate;

    @Autowired
    public SmsService(AppConfig config) {
        ExchangeFilterFunction printlnFilter = (request, next) -> {
            logger.info("\n\n" + request.method().toString().toUpperCase() +
                    ":\n\nURL:" + request.url().toString() +
                    ":\n\nHeaders:" + request.headers().toString() +
                    "\n\nAttributes:" + request.attributes() + "\n\n");
            return next.exchange(request);
        };
        this.config = config;
        restTemplate = new RestTemplate();
        // Create a list for the  converters
        List<HttpMessageConverter<?>> messageConverters = new ArrayList<>();
        MappingJackson2HttpMessageConverter messageConverter = new MappingJackson2HttpMessageConverter();
        ArrayList<MediaType> mt = new ArrayList<>();
        mt.add(MediaType.APPLICATION_JSON);
        messageConverter.setSupportedMediaTypes(mt);
        messageConverters.add(messageConverter);
        restTemplate.setMessageConverters(messageConverters);

    }

    public boolean sendSms(String phone, String text) {
        logger.info("sending sms {}, {}", phone, text);
        try {
//            HttpEntity<SmsMessage> request = new HttpEntity<>(new SmsMessage(phone, text));
            restTemplate.getForEntity(config.getSmsServerUrl() + "/sms/send?phone=" + phone + "&text=" +  UriUtils.encodePath(text, "UTF-8"),
                    String.class);
            logger.info("sent sms success");
            return true;
        } catch (Exception ex) {
            logger.error("sendSms:", ex);
            return false;
        }
    }


}
