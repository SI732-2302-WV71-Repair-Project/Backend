package com.api.repairapi.controllers;

import com.api.repairapi.models.ClientModel;
import com.api.repairapi.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;

@RestController
@RequestMapping("/client")
public class ClientController {

    @Autowired
    private ClientService clientService;

    @GetMapping
    public ArrayList<ClientModel> getClients(){
        return this.clientService.getClients();
    }

    @GetMapping(path = "/{id}")
    public Optional<ClientModel> getClientById(@PathVariable Long id){
        return this.clientService.getClientById(id);
    }

    @PostMapping
    public ClientModel saveClient(@RequestBody ClientModel client){
        return this.clientService.saveClient(client);
    }

    @PutMapping(path = "/{id}")
    public ClientModel updateClient(@RequestBody ClientModel request, @PathVariable Long id){
        return this.clientService.updateClientById(request, id);
    }

    @DeleteMapping(path = "/{id}")
    public void deleteClient(@PathVariable Long id){
        this.clientService.deleteClient(id);
    }
}
