package com.tutorial.loanservice.controller;

import com.tutorial.loanservice.entity.Loan;
import com.tutorial.loanservice.service.LoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/loan")
public class LoanController {

    @Autowired
    LoanService loanService;

    @GetMapping
    public ResponseEntity<List<Loan>> getAll() {
        List<Loan> loans = loanService.getAll();
        if(loans.isEmpty())
            return ResponseEntity.noContent().build();
        return ResponseEntity.ok(loans);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Loan> getById(@PathVariable("id") Long id) {
        Loan loan = loanService.getLoanById(id);
        if(loan == null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(loan);
    }

    @PostMapping()
    public ResponseEntity<Loan> save(@RequestBody Loan loan) {
        Loan loanNew = loanService.save(loan);
        return ResponseEntity.ok(loanNew);
    }

    @GetMapping("/byclient/{userId}")
    public ResponseEntity<List<Loan>> getByUserId(@PathVariable("userId") Long userId) {
        List<Loan> loans = loanService.byUserId(userId);
        if(loans.isEmpty())
            return ResponseEntity.noContent().build();
        return ResponseEntity.ok(loans);
    }

}
