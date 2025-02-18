package com.example.BookProject.repository;

import com.example.BookProject.LibraryData;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Repository;

import java.io.IOException;

@Repository
public class LibraryDataRepository {
    private final ObjectMapper objectMapper;

    public LibraryDataRepository(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public LibraryData loadData() throws IOException {
        ClassPathResource classPathResource = new ClassPathResource("database.json");
        LibraryData libraryData = objectMapper.readValue(classPathResource.getInputStream(), LibraryData.class);
        System.out.println(libraryData.toString());
        return libraryData;
    }

}
