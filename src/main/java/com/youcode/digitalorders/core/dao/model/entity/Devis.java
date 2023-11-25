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
@Table(name = "devis")
public class Devis {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private double description;
    private String status;

    @ManyToOne
    @JoinColumn
    private Demand demand;

}