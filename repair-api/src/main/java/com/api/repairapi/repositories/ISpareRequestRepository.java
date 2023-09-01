package com.api.repairapi.repositories;

import com.api.repairapi.models.SpareRequestModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ISpareRequestRepository extends JpaRepository<SpareRequestModel, Long> {
}
