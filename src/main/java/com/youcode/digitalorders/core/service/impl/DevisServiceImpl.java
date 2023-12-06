package com.youcode.digitalorders.core.service.impl;

import com.youcode.digitalorders.core.dao.model.entity.Demand;
import com.youcode.digitalorders.core.dao.model.entity.Devis;
import com.youcode.digitalorders.core.dao.model.entity.Equipment;
import com.youcode.digitalorders.core.dao.repository.DemandRepository;
import com.youcode.digitalorders.core.dao.repository.DevisRepository;
import com.youcode.digitalorders.core.service.DevisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class DevisServiceImpl implements DevisService {

    @Autowired
    private DevisRepository devisRepository;
    @Autowired
    private DemandRepository demandRepository;

    @Override
    public List<Devis> getAllDevis() {
        List<Devis> devisList = devisRepository.findAll();

        if (devisList.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No devis found");
        }
        return devisList;
    }

    @Override
    public ResponseEntity<Map<String,Object>> createDevi(Devis devi) {
        Demand demandToFind = demandRepository.findById(devi.getDemand().getId()).get();
        Map<String, Object> response = new HashMap<>();

        if (demandToFind != null) {
            if (demandToFind.getStatus().equals("ACCEPTED")) {
                devi.setStatus("PENDING");
                Devis deviToAdd = devisRepository.save(devi);
                response.put("status", "success");
                response.put("message", "devi add successfully");
                response.put("devi", deviToAdd);
                return ResponseEntity.ok(response);
            } else {
                response.put("status", "exception");
                response.put("message", "demand don't have ACCEPTED status!");
                return ResponseEntity.badRequest().body(response);
            }
        }
        response.put("status", "error");
        response.put("message", "Please check your demand information!");
        return ResponseEntity.badRequest().body(response);

    }

    @Override
    public ResponseEntity<Map<String,Object>> acceptDevi(Long id) {
        Optional<Devis> optionalDevis = devisRepository.findById(id);
        Map<String, Object> response = new HashMap<>();

        if (optionalDevis.isPresent()) {
            Devis existingDevi = optionalDevis.get();
            if (existingDevi.getStatus().equals("ACCEPTED")) {
                response.put("status", "error");
                response.put("message", "This Devi Already accepted!");
            } else {
                existingDevi.setStatus("ACCEPTED");
                Devis savedEntity = devisRepository.save(existingDevi);

                response.put("status", "success");
                response.put("message", "Devi Accepted successfully");
                response.put("devi", existingDevi);
            }

            return ResponseEntity.ok(response);

        } else {
            response.put("status", "error");
            response.put("message", "Devi not fond, Please check your devi id!");
            return ResponseEntity.badRequest().body(response);
        }
    }
    @Override
    public ResponseEntity<Map<String,Object>> rejectDevi(Long id) {
        Optional<Devis> optionalDevis = devisRepository.findById(id);
        Map<String, Object> response = new HashMap<>();

        if (optionalDevis.isPresent()) {
            Devis existingDevi = optionalDevis.get();
            if (existingDevi.getStatus().equals("REJECTED")) {
                response.put("status", "error");
                response.put("message", "This Devi Already Rejected!");
            } else {
                existingDevi.setStatus("REJECTED");
                devisRepository.save(existingDevi);

                response.put("status", "success");
                response.put("message", "Devi Rejected successfully");
                response.put("devi", existingDevi);
            }

            return ResponseEntity.ok(response);

        } else {
            response.put("status", "error");
            response.put("message", "Devi not fond, Please check your devi id!");
            return ResponseEntity.badRequest().body(response);
        }
    }

    @Override
    public Devis getDevisById(Long id) {
        return null;
    }

    @Override
    public Devis findByIdOrThrow(Long id) {
        return null;
    }
}
