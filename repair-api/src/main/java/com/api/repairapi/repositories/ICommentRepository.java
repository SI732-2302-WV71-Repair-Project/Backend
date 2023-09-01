package com.api.repairapi.repositories;

import com.api.repairapi.models.CommentModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICommentRepository extends JpaRepository<CommentModel, Long> {
}
