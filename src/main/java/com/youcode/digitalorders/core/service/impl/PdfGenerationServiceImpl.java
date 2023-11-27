package com.youcode.digitalorders.core.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.youcode.digitalorders.core.service.PdfGenerationService;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.Optional;

@Service
public class PdfGenerationServiceImpl implements PdfGenerationService {

    private final RestTemplate restTemplate;

    public PdfGenerationServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public <T> void generatePdfFromDatabaseObject(Optional<T> optionalDatabaseEntity, RestTemplate restTemplate) {
        optionalDatabaseEntity.ifPresent(entity -> {
            String serializedData = convertObjectToJson(entity);

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            HttpEntity<String> request = new HttpEntity<>(serializedData, headers);

            String flaskApiUrl = "http://127.0.0.1:5000/generate_pdf";

            ResponseEntity<byte[]> response = restTemplate.postForEntity(flaskApiUrl, request, byte[].class);

            if (response.getStatusCode() == HttpStatus.OK) {
                byte[] pdfBytes = response.getBody();
                // Handle the PDF bytes (e.g., save to a file, return to the user, etc.)
            } else {
                // Handle errors or other status codes from the Flask API
            }
        });
    }

    private <T> String convertObjectToJson(T object) {
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            return objectMapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            e.printStackTrace(); // or log the error
            return ""; // Return an empty string or handle the error case appropriately
        }
    }
}
