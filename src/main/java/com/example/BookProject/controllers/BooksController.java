package com.example.BookProject.controllers;

import com.example.BookProject.Book;
import com.example.BookProject.LibraryData;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.BookProject.repository.LibraryDataRepository;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/api/books")
public class BooksController {

    private final LibraryDataRepository libraryDataRepository;

    public BooksController(LibraryDataRepository libraryDataRepository) {
        this.libraryDataRepository = libraryDataRepository;
    }

    @GetMapping
    public ResponseEntity<List<Book>> getAllBooks() throws IOException {
        LibraryData libraryData = libraryDataRepository.loadData();

        return ResponseEntity.ok(libraryData.booksList());
    }

    @GetMapping("/{bookId}")
    public ResponseEntity<Book> getBookById(@PathVariable String bookId) throws IOException {
        LibraryData libraryData = libraryDataRepository.loadData();
        List<Book> books = libraryData.booksList();
        //books.stream().filter();
        Book bookToReturn = null;
        for (Book currentBook : books) {
            if (Objects.equals(bookId, currentBook.bookId())) {
                bookToReturn = currentBook;
                break;
            }
        }
        assert bookToReturn != null;
        return ResponseEntity.ok(bookToReturn);
    }
}
