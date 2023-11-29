package com.youcode.digitalorders.core.dao.model.dto;

import com.youcode.digitalorders.common.helper.ValidationHelper;
import com.youcode.digitalorders.core.dao.model.entity.Demand;
import com.youcode.digitalorders.core.dao.model.entity.Equipment;
import com.youcode.digitalorders.core.dao.model.entity.EquipmentPiece;
import com.youcode.digitalorders.core.dao.model.entity.User;
import com.youcode.digitalorders.shared.Enum.DemandeStatus;
import jakarta.validation.Validation;
import jakarta.validation.constraints.*;
import lombok.Builder;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.NumberFormat;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Builder
@Data
public class DemandDto {
    @NotNull(message = "User ID cannot be null")
    private UUID userId;

    @NotNull(message = "Equipment ID cannot be null")
    private Long equipmentId;

    @NotNull(message = "Start date cannot be null")
    @FutureOrPresent(message = "Start date must be in the present or future")
    private Date startDate;

    @NotNull(message = "End date cannot be null")
    @FutureOrPresent(message = "End date must be in the present or future")
    private Date endDate;

    @NotNull(message = "Quantity cannot be null")
    @NumberFormat(style = NumberFormat.Style.NUMBER)
    @Positive(message = "Quantity must be positive")
    @Min(value = 1, message = "Quantity must be at least 1")
    private Integer quantity;

    @AssertTrue(message = "End date must be at least 3 days after the start date")
    private boolean isEndDateBigThanStartDate() {
        return ValidationHelper.validateDatesDefirence(startDate, endDate, 3);
    }



    //TODO: check model mapper
    public Demand toEntity() {
        return Demand.builder()
                .startDate(startDate)
                .endDate(endDate)
                .quantity(quantity)
                .status(DemandeStatus.PENDING.name())
                .user(User.builder().id(userId).build())
                .equipment(Equipment.builder().id(equipmentId).build())
                .build();
    }

}
