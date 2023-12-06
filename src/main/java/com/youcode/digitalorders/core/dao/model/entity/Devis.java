    package com.youcode.digitalorders.core.dao.model.entity;

    import com.fasterxml.jackson.annotation.JsonIgnore;
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
        private Long id;
        
        private String description;
        private String status = "PENDING";

        @JsonIgnore
        @ManyToOne
        @JoinColumn(name = "demand_id")
        private Demand demand;

    }