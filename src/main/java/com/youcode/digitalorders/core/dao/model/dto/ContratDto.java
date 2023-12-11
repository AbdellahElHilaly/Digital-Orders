package com.youcode.digitalorders.core.dao.model.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.youcode.digitalorders.core.dao.model.entity.Contrat;
import com.youcode.digitalorders.core.dao.model.entity.Devis;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Builder
@Getter
@Setter

public class ContratDto {
    @NotNull(message = "devisId cannot be null")
    private Long devisId;

    @JsonCreator
    public ContratDto(@JsonProperty("devisId") Long devisId) {
        this.devisId = devisId;
    }

    public Contrat toEntity() {
        return Contrat.builder()
                .accepted_at(LocalDateTime.now())
                .status("accepted")
                .devis(Devis.builder().id(devisId).build())
                .build();
    }
}
