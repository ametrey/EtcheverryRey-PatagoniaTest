package com.tutorial.clientservice.service;

import com.tutorial.clientservice.entity.Client;
import com.tutorial.clientservice.exceptions.NotFoundException;
import com.tutorial.clientservice.feignclients.LoanFeignClient;
import com.tutorial.clientservice.model.Loan;
import com.tutorial.clientservice.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class ClientService {

    @Autowired
    ClientRepository clientRepository;

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    LoanFeignClient loanFeignClient;

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

    public Loan saveLoan(Long userId, Loan loan) {
        loan.setClientId(userId);
        Loan loanNew = loanFeignClient.save(loan);
        return loanNew;
    }
}
