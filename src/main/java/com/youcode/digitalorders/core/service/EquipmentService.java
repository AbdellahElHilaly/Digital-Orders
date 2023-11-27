package com.youcode.digitalorders.core.service;

import com.youcode.digitalorders.core.dao.model.entity.Equipment;

import java.util.List;
import java.util.Optional;

public interface EquipmentService {
    List<Equipment> selectAll();
    Optional<Equipment> selectById(Long id) ;
    Equipment addEquipment(Equipment equipment);

    Boolean deleteEquipment(Long equipment);
//      Optional<Equipment> updateEquipment(Long  id);

    Optional<Equipment> updateEquipment(Long id, Equipment updatedEquipment);

}
