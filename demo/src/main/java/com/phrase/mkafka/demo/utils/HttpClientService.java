package com.phrase.mkafka.demo.utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.phrase.mkafka.demo.config.Authn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpRequest.BodyPublisher;
import java.net.http.HttpResponse;
import java.util.List;

import static com.phrase.mkafka.demo.utils.Constants.MEMSOURCE_API_URI;
import static com.phrase.mkafka.demo.utils.Constants.MEMSOURCE_API_PATH;
import static com.phrase.mkafka.demo.utils.Constants.OBJECT_MAPPER;

@Service
public class HttpClientService {

    @Autowired
    private Authn authn;

    private HttpClient httpClient;

    public HttpClientService() {
        httpClient = HttpClient.newHttpClient();
    }

    /**
     * A basic wrapper for GET.
     *
     * @param target target in the API
     * @return GET httpRequest
     * @throws URISyntaxException
     */
    public HttpRequest httpGetRequest(final String target) throws URISyntaxException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI(MEMSOURCE_API_URI + MEMSOURCE_API_PATH + target))
                .header("Content-Type", "application/json")
                .header("Authorization", "ApiToken " + authn.apiToken())
                .GET()
                .build();

        return request;
    }

    /**
     * A basic wrapper for POST.
     *
     * @param target
     * @param bodyPublisher
     * @return POST httpRequest
     * @throws URISyntaxException
     */
    public HttpRequest httpPostRequest(final String target, final BodyPublisher bodyPublisher) throws URISyntaxException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI(MEMSOURCE_API_URI + MEMSOURCE_API_PATH + target))
                .header("Content-Type", "application/json")
                .header("Authorization", "ApiToken " + authn.apiToken())
                .POST(bodyPublisher)
                .build();

        return request;
    }

    /**
     * A basic wrapper for PUT.
     *
     * @param target
     * @param bodyPublisher
     * @return PUT httpRequest
     * @throws URISyntaxException
     */
    public HttpRequest httpPutRequest(final String target, final BodyPublisher bodyPublisher) throws URISyntaxException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI(MEMSOURCE_API_URI + MEMSOURCE_API_PATH + target))
                .header("Content-Type", "application/json")
                .header("Authorization", "ApiToken " + authn.apiToken())
                .PUT(bodyPublisher)
                .build();

        return request;
    }

    /**
     * A method for obtaining data from the response and traversing through it by node name.
     *
     * @param response httpResponse
     * @param nodeName the name of the extracted node from the response JSON's body
     * @return List of a given type
     * @param <T>
     * @throws IOException
     */
    public <T> List<T> extractListFromNode(final HttpResponse<String> response, final String nodeName)
            throws IOException {

        ObjectNode node = OBJECT_MAPPER.readValue(response.body(), ObjectNode.class);

        ObjectReader reader = OBJECT_MAPPER.readerFor(new TypeReference<List<T>>() {});

        return reader.readValue(node.get(nodeName));
    }

    public HttpClient getHttpClient() {
        return httpClient;
    }
}
