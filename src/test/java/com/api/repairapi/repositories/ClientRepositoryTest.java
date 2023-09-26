package com.api.repairapi.repositories;

import com.api.repairapi.models.ClientModel;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class ClientRepositoryTest {
    @Mock
    private IClientRepository clientRepository;

    @Test
    @DisplayName("Test for find by Id from client")
    public void testFindById() {

        // ARRANGE
        ClientModel expectedClient = new ClientModel();
        expectedClient.setId(1L);

        Mockito.when(clientRepository.findById(1L)).thenReturn(java.util.Optional.of(expectedClient));

        // ACT
        ClientModel receivedClient = clientRepository.findById(1L).orElse(null);

        // ASSERT
        Assertions.assertNotNull(receivedClient);
        Assertions.assertEquals(expectedClient.getId(), receivedClient.getId());
    }
}
