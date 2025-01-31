package com.nossaclinica_api.webserviceclient.client;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Slf4j
@Component
public class TemplateClient {
    private static final String ALL = "*/*";
    private static final String ACCEPT = "accept";
    private static final String X_API_KEY = "x-api-key";
    private static final String CONTENT_TYPE = "Content-Type";

    @Value("${nossaclinica-api.config.whatsapp-api.key}")
    private String apiKey;

    public boolean post(String uri, String json) {
        try {
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = buildNewHttpRequestPost(uri, json);
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            return response.statusCode() == 200;
        } catch (InterruptedException | IOException | URISyntaxException e) {
            log.error("Erro ao tentar enviar mensagem de whatsapp: ", e.getMessage());
        }
        return false;
    }

    private HttpRequest buildNewHttpRequestPost(String uri, String json) throws URISyntaxException {
        return HttpRequest.newBuilder()
                .uri(new URI(uri))
                .header(ACCEPT, ALL)
                .header(CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .header(X_API_KEY, apiKey)
                .POST(HttpRequest.BodyPublishers.ofString(json))
                .build();
    }


}
