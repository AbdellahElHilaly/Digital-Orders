package com.youcode.digitalorders.core.service.impl;

import com.youcode.digitalorders.core.dao.model.entity.Equipment;
import com.youcode.digitalorders.core.dao.model.entity.EquipmentPiece;
import com.youcode.digitalorders.core.dao.repository.EquipmentPieceRepository;
import com.youcode.digitalorders.core.dao.repository.EquipmentRepository;
import com.youcode.digitalorders.core.service.EquipmentPieceService;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
@AllArgsConstructor
@Service
public class EquipmentPieceServiceImpl implements EquipmentPieceService {
    @Autowired
    private EquipmentPieceRepository equipmentPieceRepository;
    private EquipmentRepository equipmentRepository;


    @Override
    public void createPieces(Long equipmentId , double price) {
        Equipment equipment = equipmentRepository.findById(equipmentId).orElseThrow(() -> new EntityNotFoundException("Equipment not found"));
        //this is gonna create the equipment rows
        for(int i = 0; i < equipment.getQuantity() ; i++){
            EquipmentPiece newEquipmentPiece = new EquipmentPiece();
            newEquipmentPiece.setEquipment(equipment);
            newEquipmentPiece.setUUID(generateUUID());
            newEquipmentPiece.setPrice(price);
            equipmentPieceRepository.save(newEquipmentPiece);
        }

    }

    @Override
    public void updatePieces(Long equipmentId, double price) {

    }

    @Override
    public Optional<EquipmentPiece> findPiecesByEquipmentId(Long equipmentId) {

        return Optional.of(equipmentPieceRepository.findById(equipmentId)).orElseThrow(() -> new EntityNotFoundException("Equipment not found"));
    }

    public List<EquipmentPiece> getAvailablePieces(Long equipmentId, Date reservationStartDate, Date reservationEndDate, int quantity) {
        Pageable pageable = PageRequest.of(0, quantity);
        return equipmentPieceRepository.findAvailablePieces(equipmentId, reservationStartDate, reservationEndDate, pageable);
    }

    @Override
    public EquipmentPiece update(EquipmentPiece equipmentPiece) {
        findByIdOrThrow(equipmentPiece.getId());
        return equipmentPieceRepository.save(equipmentPiece);

    }

    private EquipmentPiece findByIdOrThrow(Long id) {
        return equipmentPieceRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("The equipment piece with id: " + id + " not found"));
    }


    public static UUID generateUUID() {
        UUID uuid = UUID.randomUUID();
        return uuid;
    }


}
