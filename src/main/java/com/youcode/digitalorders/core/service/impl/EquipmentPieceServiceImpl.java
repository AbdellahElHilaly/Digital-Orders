package com.youcode.digitalorders.core.service.impl;

import com.youcode.digitalorders.core.dao.model.entity.Equipment;
import com.youcode.digitalorders.core.dao.model.entity.EquipmentPiece;
import com.youcode.digitalorders.core.dao.repository.EquipmentPieceRepository;
import com.youcode.digitalorders.core.dao.repository.EquipmentRepository;
import com.youcode.digitalorders.core.service.EquipmentPieceService;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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


    public static UUID generateUUID() {
        UUID uuid = UUID.randomUUID();
        return uuid;
    }


}
