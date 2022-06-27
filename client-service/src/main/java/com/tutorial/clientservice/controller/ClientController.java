package com.tutorial.clientservice.controller;

import com.tutorial.clientservice.entity.Client;
import com.tutorial.clientservice.model.Loan;
import com.tutorial.clientservice.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/client")
public class ClientController {

    public ClientService clientService;

    @Autowired
    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping
    public List<Client> getClients() {
        return clientService.getClients();
    }

    @GetMapping("/{id}")
    public Client getClientById(@PathVariable Long id) {
        return clientService.findById(id);
    }

    @PostMapping("/addClient")
    public Client addClient(@RequestBody Client client) {
        return clientService.addClient(client);
    }

    @PutMapping("/updateClient/{id}")
    public void updateClient(@PathVariable Long id, @RequestBody Client client) {
        clientService.updateClient(id, client);
    }

    @DeleteMapping("/deleteClient/{id}")
    public void deleteClient(@PathVariable Long id) {
        clientService.deleteClient(id);
    }

    @PostMapping("/save-loan/{userId}")
    public ResponseEntity<Loan> saveBike(@PathVariable("userId") Long userId, @RequestBody Loan loan) {
        if(clientService.findById(userId) == null)
            return ResponseEntity.notFound().build();
        clientService.saveLoan(userId, loan);
        return ResponseEntity.ok(loan);
    }
}
