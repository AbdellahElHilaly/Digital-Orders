package com.youcode.digitalorders.core.service;

import com.youcode.digitalorders.core.dao.model.entity.Contrat;

import java.util.List;

public interface ContratService {

    public List<Contrat> getAllContrats();
    public Contrat addContrat(Contrat contrat);
    public Contrat getContratById(Long id);
    public Contrat findByIdOrThrow(Long id);
}
