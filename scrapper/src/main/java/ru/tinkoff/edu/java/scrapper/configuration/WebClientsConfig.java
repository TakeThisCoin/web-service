package ru.tinkoff.edu.java.scrapper.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.support.WebClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;
import ru.tinkoff.edu.java.scrapper.webclients.bot.BotClient;
import ru.tinkoff.edu.java.scrapper.webclients.github.GitHubClient;
import ru.tinkoff.edu.java.scrapper.webclients.stack_overflow.StackOverflowClient;

@Validated
@Configuration
public class WebClientsConfig {

    @Bean
    GitHubClient gitHubClient(ApplicationConfig applicationConfig) {
        return createWebClient(GitHubClient.class, applicationConfig.githubApiBaseUri().toString());
    }

    @Bean
    StackOverflowClient stackOverflowClient(ApplicationConfig applicationConfig) {
        String baseUrl = applicationConfig.stackExchangeApiBaseUri()+"/"+applicationConfig.stackExchangeApiVersion();
        return createWebClient(StackOverflowClient.class, baseUrl);
    }

    @Bean
    BotClient botClient(ApplicationConfig applicationConfig) {
        return createWebClient(BotClient.class, applicationConfig.botBaseUri().toString());
    }

    private <T> T createWebClient(Class<T> clientClass, String baseUrl) {
        WebClient client = WebClient.builder().baseUrl(baseUrl).build();
        HttpServiceProxyFactory factory = HttpServiceProxyFactory.builder(WebClientAdapter.forClient(client)).build();
        return factory.createClient(clientClass);
    }
}
