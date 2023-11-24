package com.youcode.digitalorders.core.dao.model.dto;

import com.youcode.digitalorders.core.dao.model.entity.Demand;
import com.youcode.digitalorders.core.dao.model.entity.EquipmentPiece;
import com.youcode.digitalorders.core.dao.model.entity.User;
import lombok.Builder;
import lombok.Data;

import java.util.Date;
import java.util.UUID;

@Builder
@Data
public class DemandDto {
    private UUID userId;
    private Long equipmentPieceId;
    private Date startDate;
    private Date endDate;

    public Demand  toEntity(){
        return Demand.builder()
                .startDate(startDate)
                .endDate(endDate)
                .user(User.builder().id(userId).build())
                .equipmentPiece(EquipmentPiece.builder().id(equipmentPieceId).build())
                .build();
    }
}
