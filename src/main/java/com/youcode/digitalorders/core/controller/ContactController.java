package com.youcode.digitalorders.core.controller;

import com.youcode.digitalorders.core.dao.model.entity.Contrat;
import com.youcode.digitalorders.core.service.ContratService;
import com.youcode.digitalorders.shared.Const.AppEndpoints;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(AppEndpoints.Contrat_ENDPOINT)
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class ContactController {

    private final ContratService contratService;

    @GetMapping
    public ResponseEntity<List<Contrat>> getAllContrats() {
        return ResponseEntity.ok(contratService.getAllContrats());
    }
}
