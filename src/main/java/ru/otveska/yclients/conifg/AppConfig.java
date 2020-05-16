package ru.otveska.yclients.conifg;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;

@ConfigurationProperties(prefix = "app")
@Validated
@Data
public class AppConfig {

    @NotNull
    private String callbackUrl;

    private String smsServerUrl;

}
