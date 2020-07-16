package com.biblioteca.model;


public class Library {
    private Section book;
    private Section movie;

    public Library(Section book, Section movie) {
        this.book = book;
        this.movie = movie;
    }

    public String books() {
        return book.items();
    }

    public String movies() {
        return movie.items();
    }

    public boolean checkoutBook(String bookName, User user) {
        return book.checkout(bookName, user);
    }

    public boolean checkinBook(String bookName, User user) {
        return book.checkin(bookName, user);
    }

    public boolean checkoutMovie(String movieName, User user) {
        return movie.checkout(movieName, user);
    }

    public boolean checkinMovie(String movieName, User user)  {
        return movie.checkin(movieName, user);
    }

    public String checkedOutBooks() {
        return book.checkedOutItems();
    }

    public String checkedOutMovies() {
        return movie.checkedOutItems();
    }
}