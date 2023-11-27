package com.youcode.digitalorders.core.dao.repository;

import com.youcode.digitalorders.core.dao.model.entity.Devis;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DevisRepository extends JpaRepository<Devis, Long>
{
}
