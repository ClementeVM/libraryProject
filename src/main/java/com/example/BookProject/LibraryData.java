package com.example.BookProject;

import java.util.List;

public record LibraryData(List<Users> usersList, List<Books> booksList, List<Loans> loansList) {
}
