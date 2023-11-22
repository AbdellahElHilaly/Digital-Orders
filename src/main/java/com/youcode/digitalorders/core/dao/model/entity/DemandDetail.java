package com.youcode.digitalorders.core.dao.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@Data
@Entity
@Table(name = "demand_detail")
public class DemandDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private LocalDateTime borrow_date;
    private LocalDateTime return_date;

    @ManyToOne
    @JoinColumn(name = "demand_id")
    private Demand demand;

    @ManyToOne
    @JoinColumn(name = "equipment_piece_id")
    private EquipmentPiece equipmentPiece;
}