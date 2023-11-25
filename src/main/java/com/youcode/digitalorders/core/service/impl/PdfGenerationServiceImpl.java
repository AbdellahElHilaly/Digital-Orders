package com.youcode.digitalorders.core.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import liquibase.structure.DatabaseObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
@Service
public class PdfGenerationServiceImpl {

    private final RestTemplate restTemplate;

//    @Autowired
//    public PdfGenerationServiceImpl(RestTemplate restTemplate) {
//        this.restTemplate = restTemplate;
//    }

    public PdfGenerationServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public void generatePdfFromDatabaseObject(DatabaseObject databaseObject) {
        // Serialize the database object to JSON (or another suitable format)
        String serializedData = convertObjectToJson(databaseObject);

        // Set up the HTTP headers if needed (content type, etc.)
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        // Set the serialized data as the request body
        HttpEntity<String> request = new HttpEntity<>(serializedData, headers);

        // Define the Flask API endpoint URL
        String flaskApiUrl = "http://127.0.0.1:5000/generate_pdf";

        // Send the serialized data to the Flask API endpoint
        ResponseEntity<byte[]> response = restTemplate.postForEntity(flaskApiUrl, request, byte[].class);

        // Handle the response from the Flask API if needed
        if (response.getStatusCode() == HttpStatus.OK) {
            // PDF generated successfully
            byte[] pdfBytes = response.getBody();
            // Handle the PDF bytes (e.g., save to a file, return to the user, etc.)
        } else {
            // Handle errors or other status codes from the Flask API
        }
    }

    // Method to convert object to JSON (you can use libraries like Jackson)
    private String convertObjectToJson(DatabaseObject databaseObject) {
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            // Serialize the DatabaseObject to a JSON string
            return objectMapper.writeValueAsString(databaseObject);
        } catch (JsonProcessingException e) {
            // Handle any exception occurred during serialization
            e.printStackTrace(); // or log the error
            return ""; // Return an empty string or handle the error case appropriately
        }
    }
}
