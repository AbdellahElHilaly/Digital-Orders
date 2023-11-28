package com.youcode.digitalorders.core.dao.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
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

    @Column( columnDefinition = "VARCHAR(255) DEFAULT 'AVAILABLE'")
    private String status = "AVAILABLE";
    private double price;
    private String picture;


    @Column(name = "reserved_date")
    private Date reservedDate;

    @Column(name = "return_date")
    private Date returnDate;


    @ManyToOne
    @JoinColumn(name = "equipment_id")
    private Equipment equipment;
}