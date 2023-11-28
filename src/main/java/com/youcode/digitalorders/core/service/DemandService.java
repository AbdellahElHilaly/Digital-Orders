package com.youcode.digitalorders.core.service;

import com.youcode.digitalorders.core.dao.model.dto.DemandDto;
import com.youcode.digitalorders.core.dao.model.entity.Demand;
import com.youcode.digitalorders.core.dao.model.entity.EquipmentPiece;

import java.util.List;
import java.util.UUID;

public interface DemandService {

    public Demand create(Demand demand);
    public List<Demand> selectAll();
    Demand findById(UUID id);

    public List<EquipmentPiece> selectValidatedDemandsOrTrow(Demand demand);
}
