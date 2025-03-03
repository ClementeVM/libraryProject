package com.example.BookProject;

public record Loan(String loanId, boolean active, int userId, int bookId, String dateLoan) {}

