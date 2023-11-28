package com.youcode.digitalorders.core.dao.model.dto;

import com.youcode.digitalorders.core.dao.model.entity.Contrat;
import com.youcode.digitalorders.core.dao.model.entity.Devis;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Builder
@Data
public class ContratDto {


    @NotNull(message = "devis Id cannot be null")
    private Long devisId;

    public Contrat toEntity() {
        return Contrat.builder()
                .accepted_at(LocalDateTime.now())
                .status("accepted")
                .devis(Devis.builder().id(devisId).build())
                .build();
    }

}
