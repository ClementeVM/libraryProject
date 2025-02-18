package com.example.BookProject;


public record Books (String title, String author, String bookId, int availableCopies, int totalCopies){


}
//public class Books {
//    public final String title;
//    public final String author;
//    public final String bookId;
//    public final int availableCopies;
//    public final public record Books(String title, String author, String bookId, ...);
//
//    public Books(String title, String author, String bookId, int availableCopies, int totalCopies) {
//        this.title = title;
//        this.author = author;
//        this.bookId = bookId;
//        this.availableCopies = availableCopies;
//        this.totalCopies = totalCopies;
//    }
//
//    public String getTitle() {
//        return title;
//    }
//
//    public String getAuthor() {
//        return author;
//    }
//
//    public String getBookId() {
//        return bookId;
//    }
//
//    public int getAvailableCopies() {
//        return availableCopies;
//    }
//
//    public int getTotalCopies() {
//        return totalCopies;
//    }
//
//}
