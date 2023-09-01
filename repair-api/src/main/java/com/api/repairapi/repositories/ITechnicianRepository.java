package com.api.repairapi.repositories;

import com.api.repairapi.models.TechnicianModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ITechnicianRepository extends JpaRepository<TechnicianModel, Long> {
}
