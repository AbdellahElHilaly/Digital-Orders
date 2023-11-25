package com.youcode.digitalorders.core.dao.model.entity;

import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@Setter
@NoArgsConstructor
@Builder
@ToString
@Data
@Entity
@Table(name = "equipment")
public class Equipment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;
    private String description;

    //default price
    private double price ;

    @Transient
    private int Quantity ;



}