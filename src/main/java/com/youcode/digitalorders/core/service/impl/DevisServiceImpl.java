package com.youcode.digitalorders.core.service.impl;

import com.youcode.digitalorders.core.dao.model.entity.Devis;
import com.youcode.digitalorders.core.dao.model.entity.Equipment;
import com.youcode.digitalorders.core.dao.repository.DevisRepository;
import com.youcode.digitalorders.core.service.DevisService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class DevisServiceImpl implements DevisService {
    private final DevisRepository devisRepository;

    @Override
    public List<Devis> selectAll() {
        return devisRepository.findAll();
    }
}
