package com.youcode.digitalorders.core.service.impl;

import com.youcode.digitalorders.core.dao.model.entity.Contrat;
import com.youcode.digitalorders.core.dao.model.entity.Devis;
import com.youcode.digitalorders.core.dao.repository.ContratRepository;
import com.youcode.digitalorders.core.dao.repository.DevisRepository;
import com.youcode.digitalorders.core.service.ContratService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class ContratServiceImpl implements ContratService {

    @Autowired
    private ContratRepository contratRepository;
    @Autowired
    private DevisRepository devisRepository;


    @Override
    public List<Contrat> getAllContrats() {
        List<Contrat> contratList = contratRepository.findAll();
        if (contratList.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.OK, "No contrat found");
        }

        return contratList;
    }

    @Override
    public Contrat addContrat(Contrat contrat) {
        Devis validatedDevis = validateDevis(contrat.getDevis().getId());

        // Set the validated Devis to the Contrat
        contrat.setDevis(validatedDevis);

        // You can use validatedDevis as needed, for example, logging or further processing.

        return contratRepository.save(contrat);
    }


    @Override
    public Contrat getContratById(Long id) {
        return contratRepository.findById(id).orElse(null);
    }

    @Override
    public Contrat findByIdOrThrow(Long id) {
        return contratRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Contrat not found"));
    }


    private Devis validateDevis(Long devisId) {
        Optional<Devis> optionalDevis = devisRepository.findById(devisId);

        if (optionalDevis.isEmpty()) {
            throw new IllegalArgumentException("Devis with ID " + devisId + " not found.");
        }
        Devis devis = optionalDevis.get();

        if (!"accepted".equalsIgnoreCase(devis.getStatus())) {
            throw new IllegalArgumentException("Devis with ID " + devisId + " is not confirmed.");
        }

        return devis;
    }


}
