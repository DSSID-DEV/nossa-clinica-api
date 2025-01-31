package com.nossaclinica_api.webserviceclient;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nossaclinica_api.models.dtos.MessageWhatsapp;
import com.nossaclinica_api.webserviceclient.client.TemplateClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class WhatsappClient {
    private static final String CONTA = "nossa-clinica-zap";

    private final TemplateClient clientRequest;

    @Value("${nossaclinica-api.config.whatsapp-api.url}")
    private String hostWhatsappApi;

    @Value("${nossaclinica-api.config.whatsapp-api.send-message}")
    private String endPointSendMessage;

    @Value("${nossaclinica-api.config.whatsapp-api.key}")
    private String apiKey;

    public boolean post(MessageWhatsapp whatsapp) {
        var url = hostWhatsappApi.concat(endPointSendMessage).concat(CONTA);
        var response = clientRequest.post(url, parseJson(whatsapp));
        var msgLog = response ? "Mensagem enviada com sucesso para: {}" : "Falha ao tentar enviar a mensagem para: {}";
        log.info(msgLog, whatsapp.getChatId());
        return response;
    }

    private String parseJson(MessageWhatsapp whatsapp) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.writeValueAsString(whatsapp);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }
    }

}
