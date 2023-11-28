package com.youcode.digitalorders.core.service.impl;

import com.youcode.digitalorders.core.dao.model.dto.DemandDto;
import com.youcode.digitalorders.core.dao.model.entity.Demand;
import com.youcode.digitalorders.core.dao.model.entity.EquipmentPiece;
import com.youcode.digitalorders.core.dao.model.entity.User;
import com.youcode.digitalorders.core.dao.repository.DemandRepository;
import com.youcode.digitalorders.core.dao.repository.UserRepository;
import com.youcode.digitalorders.core.service.DemandService;
import com.youcode.digitalorders.core.service.EquipmentPieceService;
import com.youcode.digitalorders.core.service.EquipmentService;
import com.youcode.digitalorders.core.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class DemandServiceImpl implements DemandService {

    private final UserService userService;
    private final DemandRepository demandRepository;
    private final EquipmentPieceService equipmentPieceService;
    private final EquipmentService equipmentService;

    @Override
    public Demand create(Demand demand) {

        demand.setUser(userService.findByIdOrThrow(demand.getUser().getId()));

        demand.setEquipment(equipmentService.selectById(demand.getEquipment().getId())
                .orElseThrow(() -> new NoSuchElementException("No equipment found with id: " + demand.getEquipment().getId())));

        List<EquipmentPiece> equipmentPieceListValidated = selectValidatedDemandsOrTrow(demand);




        equipmentPieceListValidated.stream().peek(equipmentPiece -> {
            equipmentPiece.setReservedDate(demand.getStartDate());
            equipmentPiece.setReturnDate(demand.getEndDate());
            equipmentPiece.setStatus("RESERVED");
        }).forEach(equipmentPieceService::update);

        return demandRepository.save(demand);

    }

    @Override
    public List<Demand> selectAll() {
        return demandRepository.findAll();
    }

    @Override
    public Demand findById(UUID id) {
        return demandRepository.findById(id).orElseThrow(() -> new NoSuchElementException(
                "No demand found with id: " + id
        ));
    }

    @Override
    public List<EquipmentPiece> selectValidatedDemandsOrTrow(Demand demand) {

        List<EquipmentPiece> equipmentPieces = equipmentPieceService.getAvailablePieces(
                demand.getEquipment().getId(), demand.getStartDate(), demand.getEndDate(), demand.getQuantity()
        );

        if(equipmentPieces == null || equipmentPieces.isEmpty()) {
            throw new NoSuchElementException("No equipment pieces available");
        }

        if (equipmentPieces.size() < demand.getQuantity()) {
            throw new NoSuchElementException("Sorry, now we have only " + equipmentPieces.size() + " pieces available");
        }

        return equipmentPieces;

    }

    @Override
    public List<Demand> selectAllByUserId(UUID id) {
        return demandRepository.findAllByUser(userService.findByIdOrThrow(id));
    }


}


