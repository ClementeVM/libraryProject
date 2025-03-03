package com.example.BookProject.controllers;

import com.example.BookProject.Book;
import com.example.BookProject.LibraryData;
import org.springframework.http.HttpStatus;
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

        return books.stream()
                .filter(user -> Objects.equals(bookId, user.bookId()))
                .findFirst()
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/addBook")
    public ResponseEntity<String> addBook(@RequestBody Book book) {
        try {
            if (book.title() == null || book.title().isEmpty() ||
                    book.author() == null || book.author().isEmpty() ||
                    book.bookId() == null || book.bookId().isEmpty()) {
                return ResponseEntity.badRequest().body("book data not valid");
            }

            libraryDataRepository.addBook(book);
            return ResponseEntity.ok("Book added successfully");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error saving the book");
        }
    }
}
