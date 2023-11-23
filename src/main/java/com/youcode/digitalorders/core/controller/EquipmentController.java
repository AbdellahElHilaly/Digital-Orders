package com.youcode.digitalorders.core.controller;

import com.youcode.digitalorders.core.dao.model.entity.Equipment;
import com.youcode.digitalorders.core.service.EquipmentPieceService;
import com.youcode.digitalorders.core.service.EquipmentService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
public class EquipmentController {
    @Autowired
    private final EquipmentService equipmentService;
    private final EquipmentPieceService equipmentPieceService;

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
    public ResponseEntity<Equipment> add(@RequestParam String equipment_name, @RequestParam String description , @RequestParam int quantity) {
        try {
//TODO : get the data from a form with http request
            Equipment equipment = Equipment.builder()
                    .name(equipment_name)
                    .description(description)
                    .Quantity(quantity)
                    .build();

            Equipment savedEquipment = equipmentService.addEquipment(equipment);
            equipmentPieceService.createPieces(convertToLong(equipment.getId()));
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
    public  Long convertToLong(int value){
        Long longvalue = (long) value;
        return longvalue;
    }

}