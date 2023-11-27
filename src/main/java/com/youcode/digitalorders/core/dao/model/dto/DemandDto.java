package com.youcode.digitalorders.core.dao.model.dto;

import com.youcode.digitalorders.common.helper.ValidationHelper;
import com.youcode.digitalorders.core.dao.model.entity.Demand;
import com.youcode.digitalorders.core.dao.model.entity.EquipmentPiece;
import com.youcode.digitalorders.core.dao.model.entity.User;
import jakarta.validation.Validation;
import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

import java.util.Date;
import java.util.UUID;

@Builder
@Data
public class DemandDto {
    @NotNull(message = "User ID cannot be null")
    private UUID userId;

    @NotNull(message = "Equipment Piece ID cannot be null")
    private Long equipmentPieceId;

    @NotNull(message = "Start date cannot be null")
    @FutureOrPresent(message = "Start date must be in the present or future")
    private Date startDate;

    @NotNull(message = "End date cannot be null")
    @FutureOrPresent(message = "End date must be in the present or future")
    private Date endDate;

    @AssertTrue(message = "End date must be at least 3 days after the start date")
    private boolean isEndDateBigThanStartDate() {
        return ValidationHelper.validateDatesDefirence(startDate, endDate, 3);
    }

    public Demand toEntity() {
        return Demand.builder()
                .startDate(startDate)
                .endDate(endDate)
                .user(User.builder().id(userId).build())
                .equipmentPiece(EquipmentPiece.builder().id(equipmentPieceId).build())
                .build();
    }


}
