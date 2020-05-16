package ru.otveska.yclients.service.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Component;
import ru.otveska.yclients.model.UserRecordWebHookEvent;
import ru.otveska.yclients.service.SmsService;
import ru.otveska.yclients.util.YClientsAPIUtil;

import java.io.IOException;
import java.text.SimpleDateFormat;

@Component("RECORD")
public class ClientRecordHandler implements ResourceHandler {

    private static final Logger logger = LoggerFactory.getLogger(ClientRecordHandler.class);

    private final SmsService smsService;

    @Autowired
    public ClientRecordHandler(SmsService smsService) {
        this.smsService = smsService;
    }

    @Override
    public HandleResult handle(HttpEntity<String> httpEntity) {
        try {
            UserRecordWebHookEvent ur = YClientsAPIUtil.readValue(httpEntity, UserRecordWebHookEvent.class);
            String text = getSmsText(ur);
            if (text != null) {
                logger.info(text);
                boolean needSmsSend = needSmsSend(ur);
                if (needSmsSend && ur.getData().getClient().getPhone() != null) {
                    smsService.sendSms(ur.getData().getClient().getPhone(), text);
                }
            }
            return new HandleResult(true);
        } catch (IOException e) {
            if (logger.isDebugEnabled()) logger.error("Handle error: ", e);
            return new HandleResult(false);
        }

    }

    private boolean needSmsSend(UserRecordWebHookEvent ur) {
        return ur.getData().getSmsNow() == 1;
    }

    private String getSmsText(UserRecordWebHookEvent ur) {
        String text = null;
        SimpleDateFormat df = new SimpleDateFormat("dd.MM.yy HH:mm");
        switch (ur.getStatus()) {
            case CREATE:
                text = "Вы записаны в MGNails на " + df.format(ur.getData().getDate());
                break;
            case UPDATE:
                //text = "Запись в MGNails изменена на " + df.format(ur.getData().getDate());
                break;
            case DELETE:
                text = "Запись в MGNails на " + df.format(ur.getData().getDate()) + " отменена";
                break;
            default:
                text = null;
        }
        return text;
    }

    private String fullInfoRecord(UserRecordWebHookEvent ur) {
        return ur.getData().getServices().stream().map(r -> r.getTitle() + " ").reduce("", String::concat);
    }

}
