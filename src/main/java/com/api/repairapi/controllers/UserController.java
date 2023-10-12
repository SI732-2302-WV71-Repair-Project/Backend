package com.api.repairapi.controllers;

import com.api.repairapi.models.UserModel;
import com.api.repairapi.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public List<UserModel> getUsers(){
        return this.userService.getUsers();
    }

    @PostMapping
    public UserModel saveUser(@RequestBody UserModel user){
        return this.userService.saveUser(user);
    }

    @GetMapping(path = "/{id}")
    public Optional<UserModel> getUserById(@PathVariable("id") Long id){
        return this.userService.getUserById(id);
    }

    @PutMapping(path = "/{id}")
    public UserModel updateUserById(@RequestBody UserModel request, @PathVariable("id") Long id){
        return this.userService.updateById(request, id);
    }

    @PostMapping(path = "/login")
    public Optional<UserModel> getUserByEmailAndPasswords(@RequestBody UserModel user){
        return this.userService.getUserByEmailandPasswords(user.getEmail(), user.getPassword());
    }

    @DeleteMapping(path = "/{id}")
    public void deleteUserById(@PathVariable("id") Long id){
        this.userService.deleteUserById(id);
    }

}
