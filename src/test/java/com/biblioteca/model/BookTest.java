package com.biblioteca.model;

import org.junit.Test;

import static org.junit.Assert.assertEquals;


public class BookTest {

    @Test
    public void twoBooksAreSameIfThereNamesAreSame() {
        Book book1 = new Book("Head First Java", "Serran", "1990");
        Book book2 = new Book("Head First Java", "Serran", "1990");

        assertEquals(true, book1.hasTitle(book2.name()));
    }

    @Test
    public void twoBooksAreNotSameIfThereNamesAreNotSame() {
        Book book1 = new Book("Head First Java1", "Serran", "1990");
        Book book2 = new Book("Head First Java2", "Serran", "1990");

        assertEquals(false, book1.hasTitle(book2.name()));
    }

}
