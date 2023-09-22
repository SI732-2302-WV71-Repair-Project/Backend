package com.api.repairapi.repositories;

import com.api.repairapi.models.DeviceModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IDeviceRepository extends JpaRepository<DeviceModel, Long> {
}
