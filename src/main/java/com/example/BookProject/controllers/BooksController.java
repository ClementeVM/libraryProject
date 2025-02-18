package com.example.BookProject.controllers;

import com.example.BookProject.Books;
import com.example.BookProject.LibraryData;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.BookProject.repository.LibraryDataRepository;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BooksController {

    private final LibraryDataRepository libraryDataRepository;

    public BooksController(LibraryDataRepository libraryDataRepository) {
        this.libraryDataRepository = libraryDataRepository;
    }

    @GetMapping
    public ResponseEntity<List<Books>> getAllBooks() throws IOException {
        LibraryData libraryData = libraryDataRepository.loadData();

        return ResponseEntity.ok(libraryData.booksList());
    }
}
