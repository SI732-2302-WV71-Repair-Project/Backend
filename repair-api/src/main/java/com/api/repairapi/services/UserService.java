package com.api.repairapi.services;

import com.api.repairapi.models.UserModel;
import com.api.repairapi.repositories.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class UserService{

    @Autowired
    IUserRepository userRepository;

    public ArrayList<UserModel> getUsers(){
        return (ArrayList<UserModel>) userRepository.findAll();
    }

    public UserModel saveUser(UserModel user){
        return userRepository.save(user);
    }

    public Optional<UserModel> getUserById(Long id){
        return userRepository.findById(id);
    }

    public UserModel updateById(UserModel request, Long id){
        UserModel user = userRepository.findById(id).get();

        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword());
        user.setCellphoneNumber(request.getCellphoneNumber());
        user.setBirthDate(request.getBirthDate());
        user.setGender(request.getGender());
        user.setAddress(request.getAddress());
        user.setLastConnection(request.getLastConnection());

        return this.userRepository.save(user);
    }

    public void deleteUserById(Long id){
        try {
            userRepository.deleteById(id);
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
