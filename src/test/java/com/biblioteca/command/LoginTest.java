package com.biblioteca.command;

import com.biblioteca.command.Login.Input;
import com.biblioteca.command.Login.Output;
import com.biblioteca.model.Authenticator;
import com.biblioteca.model.User;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class LoginTest {

    @Test
    public void shouldTellAuthenticateToAuthenticateTheUser() {
        Authenticator authenticator = mock(Authenticator.class);
        CheckoutBook checkoutBook = mock(CheckoutBook.class);

        Login login = new Login(authenticator, checkoutBook);

        User user = new User("000-2015", "000-2015", "Student1", "student@gmail.com", "1234567890", false);
        when(authenticator.authenticate("000-2015", "000-2015")).thenReturn(user);
        when(checkoutBook.description()).thenReturn("Checkout Book");

        Input input = new Input("000-2015", "000-2015");
        Output output = login.performAction(input, null);

        assertEquals(user, output.user);
        assertEquals("Login successful", output.message);
        assertEquals("Checkout Book", output.commandsDescription[0]);
    }
}
