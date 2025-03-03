package com.example.BookProject.repository;

import com.example.BookProject.Book;
import com.example.BookProject.LibraryData;
import com.example.BookProject.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Repository
public class LibraryDataRepository {
    private final ObjectMapper objectMapper;
    private final File database;

    public LibraryDataRepository(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
        this.database = new FileSystemResource("src/main/resources/database.json").getFile();
    }

    public LibraryData loadData() throws IOException {
        ClassPathResource classPathResource = new ClassPathResource("database.json");
       return objectMapper.readValue(classPathResource.getInputStream(), LibraryData.class);
    }

    public void saveData(LibraryData libraryData) throws IOException {
        objectMapper.writeValue(database, libraryData);
    }

    public void addBook(Book book) throws IOException {
        LibraryData libraryData = loadData();
        List<Book> books = libraryData.booksList();
        if (books.stream().anyMatch(bookNew -> bookNew.bookId().equals(book.bookId()))) {
            throw new IllegalArgumentException("This book with ID " + book.bookId() + " already exists");
        }
        books.add(book);
        saveData(libraryData);
    }

    public void addUser(User user) throws IOException {
        LibraryData libraryData = loadData();
        List<User> users = libraryData.usersList();
        if (users.stream().anyMatch(userNew -> userNew.userId().equals(user.userId()))) {
            throw new IllegalArgumentException("This user with ID " + user.userId() + " already exists");
        }
        users.add(user);
        saveData(libraryData);
    }

}
