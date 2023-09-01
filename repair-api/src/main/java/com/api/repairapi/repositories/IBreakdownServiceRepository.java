package com.api.repairapi.repositories;

import com.api.repairapi.models.BreakdownServiceModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IBreakdownServiceRepository extends JpaRepository<BreakdownServiceModel, Long> {
}
