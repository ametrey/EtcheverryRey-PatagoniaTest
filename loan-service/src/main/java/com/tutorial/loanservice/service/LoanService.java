package com.tutorial.loanservice.service;

import com.tutorial.loanservice.entity.Loan;
import com.tutorial.loanservice.repository.LoanRepository;
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

    public Loan getBikeById(Long id) {
        return loanRepository.findById(id).orElse(null);
    }

    public Loan save(Loan loan) {
        Loan loanNew = loanRepository.save(loan);
        return loanNew;
    }

    public List<Loan> byUserId(Long userId) {
        return loanRepository.findByUserId(userId);
    }
}
