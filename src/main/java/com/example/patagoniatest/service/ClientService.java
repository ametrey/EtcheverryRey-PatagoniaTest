package com.example.patagoniatest.service;

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

    public Optional<Client> findById(Long id) {
        Optional<Client> opt = clientRepository.findById(id);
        return opt;
    }

    @Transactional
    public void updateClient(Long id, Client client) throws IllegalStateException {
        Optional<Client> clientRepo = clientRepository.findById(id);

        if (clientRepo.isPresent()) {
            if (!clientRepo.get().getFullName().equals(client.getFullName())
                    && !clientRepo.get().getIncome().equals(client.getIncome())) {
                clientRepository.save(client);
            }
            if (!clientRepo.get().getFullName().equals(client.getFullName())) {
                Client cli = new Client(client.getId(), client.getFullName(), clientRepo.get().getIncome());
                clientRepository.save(cli);
            }
            if (!clientRepo.get().getIncome().equals(client.getIncome())) {
                Client cli2 = new Client(client.getId(), clientRepo.get().getFullName(), client.getIncome());
                clientRepository.save(cli2);
            }
        } else {
            throw new IllegalStateException("el cliente con el id solicitado, no existe");
        }

    }

}
