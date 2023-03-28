package ru.tinkoff.edu.java.bot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;
import ru.tinkoff.edu.java.bot.configuration.ApplicationConfig;

@SpringBootApplication
@EnableConfigurationProperties(ApplicationConfig.class)
public class BotApplication {
    public static void main(String[] args) {
        ApplicationContext ctx = SpringApplication.run(BotApplication.class, args);
        BotApplication config = ctx.getBean(BotApplication.class);
        System.out.println(config);
    }
}
