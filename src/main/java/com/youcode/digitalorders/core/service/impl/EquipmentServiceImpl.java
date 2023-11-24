package com.youcode.digitalorders.core.service.impl;

import com.youcode.digitalorders.core.dao.model.entity.Equipment;
import com.youcode.digitalorders.core.dao.repository.EquipmentRepository;
import com.youcode.digitalorders.core.service.EquipmentService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class EquipmentServiceImpl implements EquipmentService {

    private final EquipmentRepository equipmentRepository;

    @Override
    public List<Equipment> selectAll() {
        return equipmentRepository.findAll();
    }

    @Override
    public Optional<Equipment> selectById(Long id) {
        return equipmentRepository.findById(id);
    }

    @Override
    public Equipment addEquipment(Equipment equipment) {
        try {
            return equipmentRepository.save(equipment);
        } catch (Exception e) {
            // Log or handle the exception appropriately
            return null;
        }
    }

    @Override
    public Boolean deleteEquipment(Long id) {
        try {
            equipmentRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            // Log or handle the exception appropriately
            return false;
        }
    }

    @Override
    public Optional<Equipment> updateEquipment(Long id, Equipment updatedEquipment) {
        Optional<Equipment> optionalEntity = equipmentRepository.findById(id);

        if (optionalEntity.isPresent()) {
            Equipment existingEntity = optionalEntity.get();

            // Update only if the corresponding field in the updated equipment is not null
            if (updatedEquipment.getName() != null) {
                existingEntity.setName(updatedEquipment.getName());
            }

            // Update other fields similarly if needed

            Equipment savedEntity = equipmentRepository.save(existingEntity);
            return Optional.of(savedEntity);
        } else {
            // Handle the case where the entity with the given ID is not found
            return Optional.empty();
        }
    }
}
