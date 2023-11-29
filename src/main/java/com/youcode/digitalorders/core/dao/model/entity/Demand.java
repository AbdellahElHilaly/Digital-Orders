package com.youcode.digitalorders.core.dao.model.entity;

import com.youcode.digitalorders.core.dao.model.dto.DemandDto;
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

    private Integer quantity;

    @Column(columnDefinition = "VARCHAR(255) DEFAULT 'PENDING'")
    private String status;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "equipment_id")
    private Equipment equipment;



    @OneToMany(mappedBy = "demand")
    @ToString.Exclude
    private List<Devis> devisList;

    public DemandDto toDto(){
        return DemandDto.builder()
                .startDate(startDate)
                .endDate(endDate)
                .userId(user.getId())
                .quantity(quantity)
//                .equipmentPieceId(equipmentPiece.getId())
                .equipmentId(equipment.getId())
                .build();
    }

}
