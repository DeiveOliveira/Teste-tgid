package com.example.Testetgid.service.impl;

import com.example.Testetgid.DTO.TransacaoResponseDTO;
import com.example.Testetgid.service.CallbackService;
import jakarta.mail.internet.MimeMessage;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CallbackServiceImpl implements CallbackService {

    public void enviarNotificacaoParaCliente(TransacaoResponseDTO responseDTO) {
        // Restante do código de envio de e-mail para o cliente
    }

    private void enviarCallback(TransacaoResponseDTO responseDTO) {
        String webhookUrl = "https://webhook.site/2549400c-3ca5-4a6d-897c-2b359b9ffdf5";

        RestTemplate restTemplate = new RestTemplate();
        restTemplate.postForEntity(webhookUrl, responseDTO, String.class);
    }

}
