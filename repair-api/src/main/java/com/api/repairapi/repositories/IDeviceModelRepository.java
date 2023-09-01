package com.api.repairapi.repositories;

import com.api.repairapi.models.DeviceModelModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IDeviceModelRepository extends JpaRepository<DeviceModelModel, Long> {
}
