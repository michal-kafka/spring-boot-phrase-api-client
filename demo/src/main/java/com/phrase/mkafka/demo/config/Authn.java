package com.phrase.mkafka.demo.config;

import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import static com.phrase.mkafka.demo.utils.Constants.MEMSOURCE_API_URI;
import static com.phrase.mkafka.demo.utils.Constants.MEMSOURCE_API_PATH;
import static com.phrase.mkafka.demo.utils.Constants.LOGIN_FILE_PATH;
import static com.phrase.mkafka.demo.utils.Constants.OBJECT_MAPPER;


@Configuration
public class Authn {

    private String apiToken;

    @Bean
    public String apiToken() {
        if (apiToken == null) {
            apiToken = obtainApiToken();
        }

        return apiToken;
    }

    private String obtainApiToken() {
        String result = null;

        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI(MEMSOURCE_API_URI + MEMSOURCE_API_PATH + "auth/login"))
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofFile(LOGIN_FILE_PATH))
                    .build();

            HttpClient httpClient = HttpClient.newHttpClient();
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

            ObjectNode node = OBJECT_MAPPER.readValue(response.body().toString(), ObjectNode.class);

            result = node.get("token").asText();
        } catch (URISyntaxException | IOException | InterruptedException ex) {
            System.err.println("An exception occurred during obtaining the API access token: " + ex.getMessage());
        }
        return result;
    }



}
