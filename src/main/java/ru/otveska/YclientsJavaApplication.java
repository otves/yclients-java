package ru.otveska;

import ru.otveska.yclients.conifg.AppConfig;
import ru.otveska.yclients.conifg.YclientsConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties({YclientsConfig.class, AppConfig.class})
public class YclientsJavaApplication {

	public static void main(String[] args) {
		SpringApplication.run(YclientsJavaApplication.class, args);
	}

}
