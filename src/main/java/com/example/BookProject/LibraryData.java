package com.example.BookProject;

import java.util.List;

public record LibraryData(List<User> usersList, List<Book> booksList, List<Loan> loansList) {
}
