package com.youcode.digitalorders.core.service;

import com.youcode.digitalorders.core.dao.model.entity.Contrat;
import com.youcode.digitalorders.core.dao.model.entity.Devis;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;

public interface DevisService {
    public List<Devis> getAllDevis();
    public ResponseEntity<Map<String,Object>> createDevi(Devis devi);
    public Devis getDevisById(Long id);
    public Devis findByIdOrThrow(Long id);
}
