package com.youcode.digitalorders.core.controller;

import com.lowagie.text.DocumentException;
import com.youcode.digitalorders.core.dao.model.dto.ContratDto;
import com.youcode.digitalorders.core.dao.model.entity.Contrat;
import com.youcode.digitalorders.core.service.ContratService;
import com.youcode.digitalorders.core.util.pdf.ContratPdfGenerator;
import com.youcode.digitalorders.shared.Const.AppEndpoints;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping(AppEndpoints.Contrat_ENDPOINT)
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class ContratController {

    private final ContratService contratService;

    private final ContratPdfGenerator contratPdfGenerator;

    @GetMapping
    public ResponseEntity<List<Contrat>> getAllContrats() {
        return ResponseEntity.ok(contratService.getAllContrats());
    }

    @PostMapping
    public Contrat addContrat(@RequestBody @Valid ContratDto contratDto,
                              HttpServletResponse response) {
        Contrat newContrat = contratService.addContrat(contratDto.toEntity());
        
        try {
            contratPdfGenerator.generate(newContrat, response);
        } catch (DocumentException | IOException e) {
            e.printStackTrace();
        }

        return newContrat;
    }

}
