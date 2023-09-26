package com.api.repairapi.controllers;

import com.api.repairapi.models.ClientModel;
import com.api.repairapi.services.ClientService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class ClientControllerTest {

    private MockMvc mockMvc;

    @Mock
    private ClientService clientService;

    @InjectMocks
    private ClientController clientController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(clientController).build();
    }

    @Test
    public void testGetClients() throws Exception {

        ArrayList<ClientModel> clients = new ArrayList<>();
        clients.add(new ClientModel());
        when(clientService.getClients()).thenReturn(clients);

        mockMvc.perform(get("/client")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));

        verify(clientService, times(1)).getClients();
    }

    @Test
    public void testGetClientById() throws Exception {
        Long clientId = 1L;
        ClientModel client = new ClientModel();
        client.setId(clientId);

        when(clientService.getClientById(clientId)).thenReturn(Optional.of(client));

        mockMvc.perform(get("/client/{id}", clientId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(client.getId()));

        verify(clientService, times(1)).getClientById(clientId);
    }

    @Test
    public void testSaveClient() throws Exception {
        ClientModel client = new ClientModel();
        client.setId(1L);

        when(clientService.saveClient(any(ClientModel.class))).thenReturn(client);

        mockMvc.perform(post("/client")
                        .content(asJsonString(client))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(client.getId()));

        verify(clientService, times(1)).saveClient(any(ClientModel.class));
    }

    @Test
    public void testUpdateClient() throws Exception {
        Long clientId = 1L;
        ClientModel client = new ClientModel();
        client.setId(clientId);

        // Simular la respuesta del servicio
        when(clientService.updateClientById(any(ClientModel.class), eq(clientId))).thenReturn(client);

        mockMvc.perform(put("/client/{id}", clientId)
                        .content(asJsonString(client))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(client.getId()));

        verify(clientService, times(1)).updateClientById(any(ClientModel.class), eq(clientId));
    }

    @Test
    public void testDeleteClient() throws Exception {
        Long clientId = 1L;

        mockMvc.perform(delete("/client/{id}", clientId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(clientService, times(1)).deleteClient(clientId);
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
