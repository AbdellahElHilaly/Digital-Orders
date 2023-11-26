package com.youcode.digitalorders.core.service;

import liquibase.structure.DatabaseObject;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

public interface PdfGenerationService {
    <T> void  generatePdfFromDatabaseObject(Optional<T> optionalDatabaseEntity, RestTemplate restTemplate);




}
