package com.biblioteca.command;

import com.biblioteca.model.Library;
import com.biblioteca.model.User;
import org.junit.Test;

import static com.biblioteca.command.CheckoutBook.Input;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;


public class CheckoutBookTest {

    @Test
    public void shouldTellLibraryToPerformCheckout() {
        Library library = mock(Library.class);
        CheckoutBook checkoutBook = new CheckoutBook(library);
        User user = new User("000-2015", "000-2015", "Student1", "student@gmail.com", "1234567890", false);

        when(library.checkoutBook("Head First With Java", user)).thenReturn(true);
        Input input = new Input("Head First With Java");
        CheckoutBook.Output output = checkoutBook.performAction(input, user);

        assertEquals("Thank you! Enjoy the book", output.message);
    }
}
