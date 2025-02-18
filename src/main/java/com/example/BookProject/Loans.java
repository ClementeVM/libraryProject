package com.example.BookProject;

public record Loans(int loanId, String type, int userId, int bookId, String dateLoan) {}

//public class Loans {
//    public int loanID;
//    public String type;
//    public String loanDate;
//
//    public int getLoanID() {
//        return loanID;
//    }
//
//    public void setLoanID(int loanID) {
//        this.loanID = loanID;
//    }
//
//    public String getType() {
//        return type;
//    }
//
//    public void setType(String type) {
//        this.type = type;
//    }
//
//    public String getLoanDate() {
//        return loanDate;
//    }
//
//    public void setLoanDate(String loanDate) {
//        this.loanDate = loanDate;
//    }
//}
