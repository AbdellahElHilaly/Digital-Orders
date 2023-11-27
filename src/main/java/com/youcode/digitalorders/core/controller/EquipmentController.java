package com.youcode.digitalorders.core.controller;

import com.youcode.digitalorders.core.dao.model.entity.Equipment;
import com.youcode.digitalorders.core.service.EquipmentPieceService;
import com.youcode.digitalorders.core.service.EquipmentService;
import com.youcode.digitalorders.core.service.PdfGenerationService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
public class EquipmentController {
    @Autowired
    private final EquipmentService equipmentService;
    private final EquipmentPieceService equipmentPieceService;


    private final PdfGenerationService pdfGenerationService;

//    public PdfController(PdfGenerationService pdfGenerationService) {
//        this.pdfGenerationService = pdfGenerationService;
//    }

//    @Autowired
//    public EquipmentController(EquipmentService equipmentService) {
//        this.equipmentService = equipmentService;
//    }


    @GetMapping("/equipments")
    public ResponseEntity<List<Equipment>> getAllEquipments() {

        return ResponseEntity.ok(equipmentService.selectAll());

    }

    //getting a user  by id

    @GetMapping("/getEquipment/{id}")
    public ResponseEntity<Equipment> getEquipment(@PathVariable("id") Long id) {
        Optional<Equipment> equipmentOptional = equipmentService.selectById(id);

        if (equipmentOptional.isPresent()) {
            return ResponseEntity.ok(equipmentOptional.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    @PostMapping("/addEquipment")
    public ResponseEntity<Equipment> add(@RequestParam String equipment_name, @RequestParam String description , @RequestParam int quantity , @RequestParam double price) {
        try {
//TODO : get the data from a form with http request
            Equipment equipment = Equipment.builder()
                    .name(equipment_name)
                    .description(description)
                    .Quantity(quantity)
                    .price(price)
                    .build();

            Equipment savedEquipment = equipmentService.addEquipment(equipment);
            //if
            equipmentPieceService.createPieces(equipment.getId() , price );
            return ResponseEntity.ok(savedEquipment);

        } catch (Exception e) {
            System.out.println(e);
            return null;
        }


    }

    @PutMapping("updateEquipment/{id}")
    public ResponseEntity<Equipment> updateEquipment(
            @PathVariable Long id,
            @RequestBody Equipment updatedEquipmentData) {

        Optional<Equipment> updatedEquipment = equipmentService.updateEquipment(id, updatedEquipmentData);

        return updatedEquipment.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }


    @DeleteMapping("deleteEquipment/{id}")
    public ResponseEntity<String> deleteEquipment(@PathVariable Long id) {
        try {
            equipmentService.deleteEquipment(id);
            return ResponseEntity.ok("Equipment with ID " + id + " deleted successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to delete equipment with ID " + id + ": " + e.getMessage());
        }
    }



    //this is jsut for tet , this end point is gonna be used in the contract and devis parts

    @PostMapping("/getPdf/{id}")
    public ResponseEntity<?> getPdf(  @PathVariable("id") Long id) {
        try {
            RestTemplate restTemplate = new RestTemplate();
            Optional<Equipment> eq = equipmentService.selectById(id); // Assuming equipmentService is available
            pdfGenerationService.generatePdfFromDatabaseObject(eq, restTemplate);
            // You might want to handle the response or return something meaningful here
            return ResponseEntity.ok("PDF generation initiated");
        }catch(Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }



}