package com.api.repairapi.repositories;

import com.api.repairapi.models.ClientModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IClientRepository extends JpaRepository<ClientModel, Long> {
}
