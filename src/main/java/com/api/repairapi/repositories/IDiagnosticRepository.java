package com.api.repairapi.repositories;

import com.api.repairapi.models.DiagnosticModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IDiagnosticRepository extends JpaRepository<DiagnosticModel,Long > {
}
