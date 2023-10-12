package com.api.repairapi.repositories;

import com.api.repairapi.models.ChatModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IChatRepository extends JpaRepository<ChatModel, Long> {
}
