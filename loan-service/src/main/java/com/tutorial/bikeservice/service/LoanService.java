package com.tutorial.bikeservice.service;

import com.tutorial.bikeservice.entity.Loan;
import com.tutorial.bikeservice.repository.LoanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoanService {

    @Autowired
    LoanRepository loanRepository;

    public List<Loan> getAll() {
        return loanRepository.findAll();
    }

    public Loan getLoanById(Long id) {
        return loanRepository.findById(id).orElse(null);
    }

    public Loan save(Loan loan) {
        Loan loanNew = loanRepository.save(loan);
        return loanNew;
    }

    public List<Loan> byUserId(Long userId) {
        return loanRepository.findByClientId(userId);
    }
}
