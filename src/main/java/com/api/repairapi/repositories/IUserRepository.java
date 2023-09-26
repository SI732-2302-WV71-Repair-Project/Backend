package com.api.repairapi.repositories;

import com.api.repairapi.models.UserModel;
import org.apache.catalina.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public interface IUserRepository extends JpaRepository<UserModel, Long> {
    Optional<UserModel> findByEmailAndPassword(String email, String password);

}
