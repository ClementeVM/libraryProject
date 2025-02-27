package com.example.BookProject.controllers;

import com.example.BookProject.User;
import com.example.BookProject.LibraryData;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.BookProject.repository.LibraryDataRepository;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/api/users")
public class UsersController {

    private final LibraryDataRepository libraryDataRepository;

    public UsersController(LibraryDataRepository libraryDataRepository) {
        this.libraryDataRepository = libraryDataRepository;
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() throws IOException {
        LibraryData libraryData = libraryDataRepository.loadData();

        return ResponseEntity.ok(libraryData.usersList());
    }

    @GetMapping("/{userId}")
    public ResponseEntity<User> getUserById(@PathVariable String userId) throws IOException {
        LibraryData libraryData = libraryDataRepository.loadData();
        List<User> users = libraryData.usersList();
        //users.stream().filter();
        User userToReturn = null;
        for (User currentUser : users) {
            if (Objects.equals(userId, currentUser.userId())) {
                userToReturn = currentUser;
                break;
            }
        }
        assert userToReturn != null;
        return ResponseEntity.ok(userToReturn);
    }
}
