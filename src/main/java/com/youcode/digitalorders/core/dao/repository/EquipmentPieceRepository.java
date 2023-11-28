package com.youcode.digitalorders.core.dao.repository;

import com.youcode.digitalorders.core.dao.model.entity.Equipment;
import com.youcode.digitalorders.core.dao.model.entity.EquipmentPiece;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface EquipmentPieceRepository extends JpaRepository<EquipmentPiece , Long> {


    @Query("SELECT ep FROM EquipmentPiece ep " +
            "WHERE ep.equipment.id = :equipmentId " +
            "AND (" +
            "    ep.status = 'AVAILABLE' " +
            "    OR " +
            "    (" +
            "        ep.reservedDate IS NOT NULL AND ep.returnDate IS NOT NULL AND " +
            "        (ep.reservedDate > :reservationStartDate OR ep.returnDate < :reservationEndDate)" +
            "    ) " +
            ")" +
            "ORDER BY ep.id")
    List<EquipmentPiece> findAvailablePieces(
            @Param("equipmentId") Long equipmentId,
            @Param("reservationStartDate") Date reservationStartDate,
            @Param("reservationEndDate") Date reservationEndDate,
            Pageable pageable
    );


}