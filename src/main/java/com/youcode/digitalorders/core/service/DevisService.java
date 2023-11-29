package com.youcode.digitalorders.core.service;

import com.youcode.digitalorders.core.dao.model.entity.Contrat;
import com.youcode.digitalorders.core.dao.model.entity.Devis;

import java.util.List;

public interface DevisService {
    public List<Devis> getAllDevis();
    public Devis addDevis(Devis devis);
    public Devis getDevisById(Long id);
    public Devis findByIdOrThrow(Long id);
}
