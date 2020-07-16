package com.biblioteca;

import com.biblioteca.command.CheckoutBook;
import com.biblioteca.command.Login;
import com.biblioteca.model.*;

import java.util.ArrayList;

public class Biblioteca {

    private static Biblioteca biblioteca;
    private CheckoutBook checkoutBook;
    private Login login;

    public static Biblioteca getInstance() {
        if (biblioteca == null) {
            biblioteca = new Biblioteca();
        }
        return biblioteca;
    }

    private Biblioteca() {
        Authenticator authenticator = new Authenticator(getUsers());
        Library library = getLibrary();

        this.checkoutBook = new CheckoutBook(library);
        this.login = new Login(authenticator, checkoutBook);
    }

    public Login.Output performLogin(Login.Input input) {
        return login.performAction(input, null);
    }

    public CheckoutBook.Output performCheckoutBook(CheckoutBook.Input input, User user) {
        return checkoutBook.performAction(input, user);
    }

    private static Library getLibrary() {
        Section bookSection = new Section(getBooks());
        Section movieSection = new Section(getMovies());
        return new Library(bookSection, movieSection);
    }

    private static ArrayList<Item> getBooks() {
        ArrayList<Item> books = new ArrayList<>();
        Book book1 = new Book("Head First Java", "Bert Bates", "2003");
        Book book2 = new Book("Complete Reference", "Java Author", "2001");
        books.add(book1);
        books.add(book2);
        return books;
    }

    private static ArrayList<Item> getMovies() {
        ArrayList<Item> movies = new ArrayList<>();
        Movie movie = new Movie("The Shawshank Redemption", "1994", "Frank Darabont", 10);
        movies.add(movie);
        return movies;
    }

    private static ArrayList<User> getUsers() {
        ArrayList<User> users = new ArrayList<>();
        User user1 = new User("2015-001", "p@ssword", "Lucy", "lucy@biblioteca.com", "44549807", false);
        User user2 = new User("2015-002", "p@ssword", "Peter", "peter@biblioteca.com", "44657885", false);
        User user3 = new User("2014-001", "p@ssword", "Andy", "andy@biblioteca.com", "44528790", true);
        users.add(user1);
        users.add(user2);
        users.add(user3);
        return users;
    }
}
