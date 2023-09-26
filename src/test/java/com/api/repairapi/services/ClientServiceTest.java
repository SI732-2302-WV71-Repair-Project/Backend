package com.api.repairapi.services;


import com.api.repairapi.models.ClientModel;
import com.api.repairapi.models.UserModel;
import com.api.repairapi.repositories.IClientRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;

@ExtendWith(MockitoExtension.class)
public class ClientServiceTest {

    @InjectMocks
    private ClientService clientService;

    @Mock
    private IClientRepository clientRepository;

    @Test
    public void testGetClients() {
        // Arrange
        UserModel userCarlos = new UserModel();
        userCarlos.setFirstName("Carlos");
        UserModel userJose = new UserModel();
        userJose.setFirstName("Jose");

        ClientModel client1 = new ClientModel();
        client1.setUser(userCarlos); // Aquí se establece una instancia de UserModel, no un String.

        ClientModel client2 = new ClientModel();
        client2.setUser(userJose); // Lo mismo aquí.

        ArrayList<ClientModel> expectedClients = new ArrayList<>(Arrays.asList(client1, client2));

        Mockito.when(clientRepository.findAll()).thenReturn(expectedClients);

        // Act
        ArrayList<ClientModel> actualClients = clientService.getClients();

        // Assert
        Assertions.assertEquals(expectedClients.size(), actualClients.size());
    }

    @Test
    public void testGetClientById() {
        // Arrange
        UserModel userCarlos = new UserModel();
        userCarlos.setFirstName("Carlos");

        ClientModel client = new ClientModel();
        client.setUser(userCarlos);
        Long id = 1L;

        Mockito.when(clientRepository.findById(id)).thenReturn(Optional.of(client));

        // Act
        Optional<ClientModel> actualClient = clientService.getClientById(id);

        // Assert
        Assertions.assertTrue(actualClient.isPresent());
        Assertions.assertEquals(client, actualClient.get());
    }

    @Test
    public void testSaveClient() {
        // Arrange
        UserModel userCarlos = new UserModel();
        userCarlos.setFirstName("Carlos");

        ClientModel client = new ClientModel();
        client.setUser(userCarlos);

        Mockito.when(clientRepository.save(client)).thenReturn(client);

        // Act
        ClientModel savedClient = clientService.saveClient(client);

        // Assert
        Assertions.assertEquals(client, savedClient);
    }

    @Test
    public void testUpdateClientById() {
        // Arrange
        UserModel userCarlos = new UserModel();
        userCarlos.setFirstName("Carlos");

        UserModel userJose = new UserModel();
        userJose.setFirstName("Jose");

        ClientModel originalClient = new ClientModel();
        originalClient.setUser(userCarlos);

        Long id = 1L;
        ClientModel requestClient = new ClientModel();
        requestClient.setUser(userJose);

        Mockito.when(clientRepository.findById(id)).thenReturn(Optional.of(originalClient));
        Mockito.when(clientRepository.save(originalClient)).thenReturn(originalClient);

        // Act
        ClientModel updatedClient = clientService.updateClientById(requestClient, id);

        // Assert
        Assertions.assertEquals(requestClient.getUser().getFirstName(), updatedClient.getUser().getFirstName());
    }

    @Test
    public void testDeleteClientSuccessfully() {
        Long id = 1L;

        doNothing().when(clientRepository).deleteById(id);

        // Act & Assert
        clientService.deleteClient(id);
    }

    @Test
    public void testDeleteClientWithException() {
        Long id = 1L;

        doThrow(new RuntimeException("Database error")).when(clientRepository).deleteById(id);

        clientService.deleteClient(id);
    }
}
