package com.api.repairapi.controllers;

import com.api.repairapi.models.UserModel;
import com.api.repairapi.services.UserService;
import org.apache.catalina.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.ArrayList;
import java.util.Optional;
import com.fasterxml.jackson.databind.ObjectMapper;

public class UserControllerTest {

    private MockMvc mockMvc;

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
    }

    @Test
    public void testGetUsers() throws Exception{
        ArrayList<UserModel> users = new ArrayList<UserModel>();
        users.add(new UserModel());
        when(userService.getUsers()).thenReturn(users);

        mockMvc.perform(get("/users")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));

        verify(userService, times(1)).getUsers();
    }
    @Test
    public void testSaveUser() throws Exception {
        UserModel user = new UserModel();
        user.setId(1L);

        // Simular la respuesta del servicio
        when(userService.saveUser(any(UserModel.class))).thenReturn(user);

        mockMvc.perform(post("/users")
                        .content(asJsonString(user))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(user.getId()));

        verify(userService, times(1)).saveUser(any(UserModel.class));
    }

    @Test
    public void testGetUserById() throws Exception {
        Long userId = 1L;
        UserModel user = new UserModel();
        user.setId(userId);

        // Simular la respuesta del servicio
        when(userService.getUserById(userId)).thenReturn(Optional.of(user));

        mockMvc.perform(get("/users/{id}", userId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(user.getId()));

        verify(userService, times(1)).getUserById(userId);
    }

    @Test
    public void testUpdateUserById() throws Exception {
        Long userId = 1L;
        UserModel user = new UserModel();
        user.setId(userId);

        // Simular la respuesta del servicio
        when(userService.updateById(any(UserModel.class), eq(userId))).thenReturn(user);

        mockMvc.perform(put("/users/{id}", userId)
                        .content(asJsonString(user))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(user.getId()));

        verify(userService, times(1)).updateById(any(UserModel.class), eq(userId));
    }
    @Test
    public void testGetUserByEmailandPasswords() throws Exception {
        UserModel user = new UserModel();
        user.setEmail("example@example.com");
        user.setPassword("password123");

        // Simular la respuesta del servicio
        when(userService.getUserByEmailandPasswords(user.getEmail(), user.getPassword())).thenReturn(Optional.of(user));

        mockMvc.perform(post("/users/login")
                        .content(asJsonString(user))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.email").value(user.getEmail()))
                .andExpect(jsonPath("$.password").value(user.getPassword()));

        verify(userService, times(1)).getUserByEmailandPasswords(user.getEmail(), user.getPassword());
    }
    @Test
    public void testDeleteUserById() throws Exception {
        Long userId = 1L;

        mockMvc.perform(delete("/users/{id}", userId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(userService, times(1)).deleteUserById(userId);
    }

    // Utilidad para convertir objetos a JSON
    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
