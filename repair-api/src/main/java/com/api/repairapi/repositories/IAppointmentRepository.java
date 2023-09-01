package com.api.repairapi.repositories;

import com.api.repairapi.models.AppointmentModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IAppointmentRepository extends JpaRepository<AppointmentModel, Long> {
}
