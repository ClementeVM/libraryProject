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
        //loans.stream().filter();
        Loan loanToReturn = null;
        for (Loan currentLoan : loans) {
            if (Objects.equals(loanId, currentLoan.loanId())) {
                loanToReturn = currentLoan;
                break;
            }
        }
        assert loanToReturn != null;
        return ResponseEntity.ok(loanToReturn);
    }
}
