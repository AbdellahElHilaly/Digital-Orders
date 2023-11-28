package com.youcode.digitalorders.core.service;

import com.youcode.digitalorders.core.dao.model.entity.EquipmentPiece;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface EquipmentPieceService {
    void createPieces(Long equipmentId , double price); //this is the method that handles the equipment creation in the Equipment_pieces table

    void updatePieces(Long equipmentId , double price);

    Optional<EquipmentPiece> findPiecesByEquipmentId(Long equipmentId);

    public List<EquipmentPiece> getAvailablePieces(Long equipmentId, Date reservationStartDate, Date reservationEndDate, int quantity);

    EquipmentPiece update(EquipmentPiece equipmentPiece);
}
