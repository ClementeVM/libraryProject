package com.example.BookProject.controllers;

import com.example.BookProject.Loan;
import com.example.BookProject.LibraryData;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.BookProject.repository.LibraryDataRepository;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/api/loans")
public class LoansController {

    private final LibraryDataRepository libraryDataRepository;

    public LoansController(LibraryDataRepository libraryDataRepository) {
        this.libraryDataRepository = libraryDataRepository;
    }

    @GetMapping
    public ResponseEntity<List<Loan>> getAllLoans() throws IOException {
        LibraryData libraryData = libraryDataRepository.loadData();

        return ResponseEntity.ok(libraryData.loansList());
    }
    @GetMapping("/{loanId}")
    public ResponseEntity<Loan> getLoanById(@PathVariable String loanId) throws IOException {
        LibraryData libraryData = libraryDataRepository.loadData();
        List<Loan> loans = libraryData.loansList();

        return loans.stream()
                .filter(loan -> Objects.equals(loanId, loan.loanId()))
                .findFirst()
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
