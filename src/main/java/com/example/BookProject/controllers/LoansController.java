package com.example.BookProject.controllers;

import com.example.BookProject.Loans;
import com.example.BookProject.LibraryData;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.BookProject.repository.LibraryDataRepository;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/loans")
public class LoansController {

    private final LibraryDataRepository libraryDataRepository;

    public LoansController(LibraryDataRepository libraryDataRepository) {
        this.libraryDataRepository = libraryDataRepository;
    }

    @GetMapping
    public ResponseEntity<List<Loans>> getAllLoans() throws IOException {
        LibraryData libraryData = libraryDataRepository.loadData();

        return ResponseEntity.ok(libraryData.loansList());
    }
}
