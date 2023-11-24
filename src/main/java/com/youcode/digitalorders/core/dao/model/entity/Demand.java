package com.youcode.digitalorders.core.dao.model.entity;

import com.youcode.digitalorders.shared.Enum.DemandeStatus;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@Getter
@Setter
@Entity
@Table(name = "demande")
public class Demand {
    @Id
    @GeneratedValue(generator = "uuid2")
    private UUID id;

    private Double price;
    @Column(columnDefinition = "VARCHAR(255) DEFAULT 'PENDING'")
    private DemandeStatus status;

    @ManyToOne
    private User user;

    @ManyToOne
    private EquipmentPiece equipmentPiece;

    @OneToMany
    @ToString.Exclude
    private List<Devis> devisList;

}