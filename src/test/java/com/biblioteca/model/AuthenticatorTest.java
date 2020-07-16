package com.biblioteca.model;

import org.junit.Test;

import java.util.ArrayList;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.core.IsNull.nullValue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

public class AuthenticatorTest {

    @Test
    public void shouldAuthenticateUser() {
        ArrayList<User> users = new ArrayList<User>();
        User expectedUser = new User("000-2015", "000-2015", "Student1", "student@gmail.com", "1234567890", false);
        users.add(expectedUser);
        Authenticator authenticator = new Authenticator(users);

        User actualUser = authenticator.authenticate("000-2015", "000-2015");

        assertThat(actualUser, is(expectedUser));
    }

    @Test
    public void shouldReturnNullGivenNoSuchUserExists() {
        ArrayList<User> users = new ArrayList<User>();
        User loggedUser = new User("000-2015", "000-2015", "Student1", "student@gmail.com", "1234567890", false);
        users.add(loggedUser);
        Authenticator authenticator = new Authenticator(users);
        String wrongLibraryNumber = "666-2019";
        String wrongPassword = "666-2019";

        User user = authenticator.authenticate(wrongLibraryNumber, wrongPassword);

        assertThat(user, is(nullValue()));
    }
}