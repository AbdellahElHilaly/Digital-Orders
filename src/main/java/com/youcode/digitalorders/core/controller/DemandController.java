package com.youcode.digitalorders.core.controller;


import com.youcode.digitalorders.core.dao.model.dto.DemandDto;
import com.youcode.digitalorders.core.dao.model.entity.Demand;
import com.youcode.digitalorders.core.service.DemandService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.youcode.digitalorders.shared.Const.AppEndpoints.DEMAND_ENDPOINT;

@RestController
@RequestMapping(DEMAND_ENDPOINT)
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class DemandController {

    private final DemandService demandService;

    @PostMapping
    public ResponseEntity<Demand> create(@RequestBody @Valid DemandDto demandDto) {
        return ResponseEntity.ok(demandService.create(demandDto.toEntity()));
    }





}
