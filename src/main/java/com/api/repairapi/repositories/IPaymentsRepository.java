package com.api.repairapi.repositories;

import com.api.repairapi.models.PaymentsModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IPaymentsRepository extends JpaRepository<PaymentsModel, Long> {
}
