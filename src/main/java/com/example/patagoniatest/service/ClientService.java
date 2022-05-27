package com.example.patagoniatest.service;

import com.example.patagoniatest.exceptions.NotFoundException;
import com.example.patagoniatest.model.Client;
import com.example.patagoniatest.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class ClientService {

    private final ClientRepository clientRepository;

    @Autowired
    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public List<Client> getClients() {
        return clientRepository.findAll();
    }

    public Client addClient(Client client) {
        return clientRepository.save(client);
    }

    public void deleteClient(Long id) {
        clientRepository.deleteById(id);
    }

    public Client findById(Long id) {
        Optional<Client> opt = clientRepository.findById(id);
        return opt.orElseThrow(() -> new NotFoundException(id));
    }

    @Transactional
    public void updateClient(Long id, Client client) throws IllegalStateException {

        Optional<Client> clientOpt = clientRepository.findById(id);

        if (!clientOpt.isEmpty()) {
            if (!clientOpt.get().getFullName().equals(client.getFullName())) {
                clientOpt.get().setFullName(client.getFullName());
            }
            if (!clientOpt.get().getIncome().equals(client.getIncome())) {
                clientOpt.get().setIncome(client.getIncome());
            }
            clientRepository.save(clientOpt.get());
        }
        clientOpt.orElseThrow(() -> new IllegalStateException("el cliente con el id solicitado, no existe"));
    }
}
