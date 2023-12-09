package com.youcode.digitalorders.core.controller;

import com.youcode.digitalorders.core.dao.model.entity.Devis;
import com.youcode.digitalorders.core.service.DevisService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static com.youcode.digitalorders.shared.Const.AppEndpoints.DEVIS_ENDPOINT;

@RestController
@RequestMapping(DEVIS_ENDPOINT)
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class DevisController {

    private final DevisService devisService;

    @GetMapping()
    public ResponseEntity<List<Devis>> getAllDevis() {
        return ResponseEntity.ok(devisService.getAllDevis());
    }

    @GetMapping("accepted-devis")
    public ResponseEntity<List<Devis>> getAcceptedDevis() {
        return ResponseEntity.ok(devisService.getAcceptedDevis());
    }

    @PostMapping()
    public ResponseEntity<Map<String, Object>> createDevi(@RequestBody @Valid Devis devi) {
        return  devisService.createDevi(devi);
    }
    @PostMapping("accept-devi/{id}")
    public ResponseEntity<Map<String, Object>> acceptDevi(@PathVariable Long id) {
        return devisService.acceptDevi(id);
    }
    @PostMapping("reject-devi/{id}")
    public ResponseEntity<Map<String, Object>> rejectDevi(@PathVariable Long id) {
        return devisService.rejectDevi(id);
    }

}
