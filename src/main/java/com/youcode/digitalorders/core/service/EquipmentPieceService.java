package com.youcode.digitalorders.core.service;

import com.youcode.digitalorders.core.dao.model.entity.Equipment;

public interface EquipmentPieceService {
    void createPieces(Long equipmentId , double price); //this is the method that handles the equipment creation in the Equipment_pieces table

    void updatePieces(Long equipmentId , double price);

    //this method should take the equipment id and updates it in the equipment_pieces table
    //and updates the price in the equipment table




}
