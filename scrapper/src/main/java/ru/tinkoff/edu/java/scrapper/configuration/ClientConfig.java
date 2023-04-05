package ru.tinkoff.edu.java.scrapper.configuration;

import org.springframework.boot.context.properties.bind.DefaultValue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.support.WebClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;
import ru.tinkoff.edu.java.scrapper.webclients.GitHubClient;
import ru.tinkoff.edu.java.scrapper.webclients.StackOverflowClient;

@Configuration
public class ClientConfig {

    @Bean
    GitHubClient gitHubClient(ApplicationConfig applicationConfig) {
        return createWebClient(GitHubClient.class, applicationConfig.githubApiPath().toString());
    }

    @Bean
    StackOverflowClient stackOverflowClient(ApplicationConfig applicationConfig) {
        return createWebClient(StackOverflowClient.class, applicationConfig.stackExchangeApiPath().toString()+"/"+applicationConfig.stackExchangeApiVersion());
    }

    private <T> T createWebClient(Class<T> clientClass, String baseUrl) {
        WebClient client = WebClient.builder().baseUrl(baseUrl).build();
        HttpServiceProxyFactory factory = HttpServiceProxyFactory.builder(WebClientAdapter.forClient(client)).build();

        return factory.createClient(clientClass);
    }
}
