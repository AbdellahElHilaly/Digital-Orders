package com.youcode.digitalorders.core.dao.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@Data
@Entity
@Table(name = "demande")
public class Demand {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private double price;
    private String status;

    @OneToMany
    private List<DemandDetail> demandDetails;

    @ManyToOne
    @JoinColumn
    private User user;

    @OneToMany
    private List<Devis> devisList;
}