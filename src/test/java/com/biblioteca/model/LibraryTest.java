package com.biblioteca.model;

import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;


public class LibraryTest {

    @Test
    public void shouldCallSectionListItemsForBooks() {
        Section book = mock(Section.class);
        Section movie = mock(Section.class);
        Library library = new Library(book, movie);

        library.books();

        verify(book).items();
    }

    @Test
    public void shouldCallSectionListItemsForMovies() {
        Section book = mock(Section.class);
        Section movie = mock(Section.class);
        Library library = new Library(book, movie);

        library.movies();

        verify(movie).items();
    }

    @Test
    public void shouldCallSectionCheckoutForBook() {
        Section book = mock(Section.class);
        Section movie = mock(Section.class);
        User user = mock(User.class);
        Library library = new Library(book, movie);

        library.checkoutBook("Head First With Java", user);

        verify(book).checkout("Head First With Java", user);
    }

    @Test
    public void shouldCallSectionCheckinForBook() {
        Section book = mock(Section.class);
        Section movie = mock(Section.class);
        User user = mock(User.class);
        Library library = new Library(book, movie);

        library.checkinBook("Head First With Java", user);

        verify(book).checkin("Head First With Java", user);
    }

    @Test
    public void shouldCallSectionCheckoutForMovie() {
        Section book = mock(Section.class);
        Section movie = mock(Section.class);
        User user = mock(User.class);
        Library library = new Library(book, movie);

        library.checkoutMovie("ABCD2", user);

        verify(movie).checkout("ABCD2", user);
    }

    @Test
    public void shouldCallSectionCheckinForMovie() {
        Section book = mock(Section.class);
        Section movie = mock(Section.class);
        User user = mock(User.class);
        Library library = new Library(book, movie);

        library.checkinMovie("ABCD2", user);

        verify(movie).checkin("ABCD2", user);
    }

    @Test
    public void shouldCallSectionListCheckedItemsForBooks() {
        Section book = mock(Section.class);
        Section movie = mock(Section.class);
        Library library = new Library(book, movie);

        library.checkedOutBooks();

        verify(book).checkedOutItems();
    }

    @Test
    public void shouldCallSectionListCheckedItemsForMovies() {
        Section book = mock(Section.class);
        Section movie = mock(Section.class);
        Library library = new Library(book, movie);

        library.checkedOutMovies();

        verify(movie).checkedOutItems();
    }
}