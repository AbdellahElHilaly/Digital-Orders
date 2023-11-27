package com.youcode.digitalorders.core.dao.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@Getter
@Setter
@Entity
@Table(name = "equipment_piece")
public class EquipmentPiece {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private UUID UUID;  // equipment identifier
    private String status; // available or not
    private double price;
    private String picture;

    @ManyToOne
    @JoinColumn(name = "equipment_id")
    private Equipment equipment;
}