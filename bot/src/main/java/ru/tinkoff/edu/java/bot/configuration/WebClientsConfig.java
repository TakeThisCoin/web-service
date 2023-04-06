package ru.tinkoff.edu.java.bot.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.support.WebClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;
import ru.tinkoff.edu.java.bot.webclients.ScrapperClient;

@Validated
@Configuration
public class WebClientsConfig {

    @Bean
    ScrapperClient scrapperWebClient(ApplicationConfig applicationConfig) {
        return createWebClient(ScrapperClient.class, applicationConfig.scrapperApiBaseUri().toString());
    }

    private <T> T createWebClient(Class<T> clientClass, String baseUrl) {
        WebClient client = WebClient.builder().baseUrl(baseUrl).build();
        HttpServiceProxyFactory factory = HttpServiceProxyFactory.builder(WebClientAdapter.forClient(client)).build();
        return factory.createClient(clientClass);
    }
}
