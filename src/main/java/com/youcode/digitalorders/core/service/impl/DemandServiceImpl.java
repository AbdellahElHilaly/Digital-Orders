package com.youcode.digitalorders.core.service.impl;

import com.youcode.digitalorders.core.dao.model.entity.Demand;
import com.youcode.digitalorders.core.dao.repository.DemandRepository;
import com.youcode.digitalorders.core.service.DemandService;
import com.youcode.digitalorders.core.service.EquipmentPieceService;
import com.youcode.digitalorders.core.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class DemandServiceImpl implements DemandService {

    private final UserService userService;
    private final DemandRepository demandRepository;
    private final EquipmentPieceService equipmentPieceService;
    @Override
    public Demand create(Demand demand) {
        demand.setUser(userService.findByIdOrThrow(demand.getUser().getId()));
        demand.setEquipmentPiece(equipmentPieceService.findPiecesByEquipmentId(demand.getEquipmentPiece().getId()).orElseThrow());
        return demandRepository.save(demand);
    }
}
