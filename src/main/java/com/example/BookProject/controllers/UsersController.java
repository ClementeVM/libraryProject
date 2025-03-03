package com.example.BookProject.controllers;

import com.example.BookProject.User;
import com.example.BookProject.LibraryData;
import org.springframework.http.HttpStatus;
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

        return users.stream()
                .filter(user -> Objects.equals(userId, user.userId()))
                .findFirst()
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/addUser")
    public ResponseEntity<String> addUser(@RequestBody User user) {
        try {
            if (user.userId() == null || user.userId().isEmpty() ||
                    user.userRole() == null || user.userRole().isEmpty() ||
                    user.userName() == null || user.userName().isEmpty() ||
                    user.password() == null || user.password().isEmpty() ||
                    user.email() == null || user.email().isEmpty()) {
                return ResponseEntity.badRequest().body("All these information is required");
            }

            libraryDataRepository.addUser(user);
            return ResponseEntity.ok("User added successfully");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error saving the user");
        }
    }
}
