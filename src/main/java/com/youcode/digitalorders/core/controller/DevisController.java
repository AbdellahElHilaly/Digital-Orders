package com.youcode.digitalorders.core.controller;

import com.youcode.digitalorders.core.dao.model.entity.Devis;
import com.youcode.digitalorders.core.service.DevisService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.youcode.digitalorders.shared.Const.AppEndpoints.DEVIS_ENDPOINT;

@RestController
@RequestMapping(DEVIS_ENDPOINT)
@AllArgsConstructor(onConstructor = @__(@Autowired))

public class DevisController {

    private final DevisService devisService;

    @GetMapping()
    public ResponseEntity<List<Devis>> getAllEquipments() {
        return ResponseEntity.ok(devisService.getAllDevis());
    }
}
