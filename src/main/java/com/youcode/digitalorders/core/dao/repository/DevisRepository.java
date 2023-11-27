package com.youcode.digitalorders.core.dao.repository;

import com.youcode.digitalorders.core.dao.model.entity.Devis;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface DevisRepository extends JpaRepository<Devis, UUID> {

}
