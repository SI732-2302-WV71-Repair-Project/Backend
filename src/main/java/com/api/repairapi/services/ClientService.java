package com.api.repairapi.services;

import com.api.repairapi.models.ClientModel;
import com.api.repairapi.repositories.IClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class ClientService {

    @Autowired
    private IClientRepository clientRepository;

    public ArrayList<ClientModel> getClients(){
        return (ArrayList<ClientModel>) this.clientRepository.findAll();
    }

    public Optional<ClientModel> getClientById(Long id){
        return this.clientRepository.findById(id);
    }

    public ClientModel saveClient(ClientModel client){
        return this.clientRepository.save(client);
    }

    public ClientModel updateClientById(ClientModel request, Long id){
        ClientModel client = this.clientRepository.findById(id).get();

        client.setUser(request.getUser());

        return  this.clientRepository.save(client);
    }

    public void deleteClient(Long id){
        try {
            this.clientRepository.deleteById(id);
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

}
