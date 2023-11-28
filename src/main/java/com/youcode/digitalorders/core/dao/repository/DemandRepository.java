package com.youcode.digitalorders.core.dao.repository;

import com.youcode.digitalorders.core.dao.model.dto.DemandDto;
import com.youcode.digitalorders.core.dao.model.entity.Demand;
import com.youcode.digitalorders.core.dao.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface DemandRepository extends JpaRepository<Demand, UUID> {

    List<Demand> findAllByUser(User user);
}
