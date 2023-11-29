package com.youcode.digitalorders.core.service.impl;

import com.youcode.digitalorders.core.dao.model.entity.Devis;
import com.youcode.digitalorders.core.dao.repository.DevisRepository;
import com.youcode.digitalorders.core.service.DevisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class DevisServiceImpl implements DevisService {

    @Autowired
    private DevisRepository devisRepository;

    @Override
    public List<Devis> getAllDevis() {
        List<Devis> devisList = devisRepository.findAll();

        if (devisList.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No devis found");
        }
        return devisList;
    }

    @Override
    public Devis addDevis(Devis devis) {
        return null;
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
