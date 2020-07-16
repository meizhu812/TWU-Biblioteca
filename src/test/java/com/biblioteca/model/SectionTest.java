package com.biblioteca.model;

import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;

public class SectionTest {

    @Test
    public void shouldReturnTheBooks() {
        ArrayList<Item> books = new ArrayList<Item>();
        Book book1 = new Book("Head First With Java", "Serran", "1990");
        books.add(book1);
        Section section = new Section(books);

        assertEquals(String.format("Head First With Java|Serran|1990|\n"), section.items());
    }

    @Test
    public void shouldCheckoutBookIfAvailable() {
        ArrayList<Item> books1 = new ArrayList<Item>();
        Book book1 = new Book("Head First With Java", "Serran", "1990");
        Book book2 = new Book("Two States", "Chetan Bhagat", "2000");
        books1.add(book1);
        books1.add(book2);
        Section section = new Section(books1);
        ArrayList<Book> books2 = new ArrayList<Book>();
        books2.add(book1);
        User user = new User("666","password", "byleto", "user@twu.com", "666-89076", false);

        section.checkout(book2.name(), user);

        assertEquals("Head First With Java|Serran|1990|\n", section.items());
    }

    @Test
    public void shouldCheckinBookIfIssued() {
        ArrayList<Item> books1 = new ArrayList<Item>();
        Book book1 = new Book("Head First With Java", "Serran", "1990");
        Book book2 = new Book("Two States", "Chetan Bhagat", "2000");
        books1.add(book1);
        books1.add(book2);
        Section section = new Section(books1);
        ArrayList<Book> books2 = new ArrayList<Book>();
        books2.add(book1);
        books2.add(book2);
        User user = new User("000-2015", "000-2015", "Student1", "student@gmail.com", "1234567890", false);

        section.checkout(book2.name(), user);
        section.checkin(book2.name(), user);

        assertEquals("Head First With Java|Serran|1990|\nTwo States|Chetan Bhagat|2000|\n", section.items());
    }
}