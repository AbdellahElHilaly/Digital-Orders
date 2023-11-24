package com.youcode.digitalorders.core.dao.model.entity;

import com.youcode.digitalorders.shared.Enum.DemandeStatus;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
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

    @Column(name = "start_date")
    private Date startDate;
    @Column(name = "end_date")
    private Date endDate;

    private Double price;
    @Column(columnDefinition = "VARCHAR(255) DEFAULT 'PENDING'")
    private DemandeStatus status;

    @ManyToOne
    private User user;

    @ManyToOne
    private EquipmentPiece equipmentPiece;

    @OneToMany
    @JoinTable(name = "demande_devis_list")
    @ToString.Exclude
    private List<Devis> devisList;

}