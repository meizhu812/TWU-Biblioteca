package com.biblioteca.model;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class UserTest {

    @Test
    public void shouldReturnTrueIfLoginDetailsMatch() {
        User user = new User("001-2015", "001-2015", "Student1", "student@gmail.com", "1234567890", false);

        assertEquals(true, user.hasLoginCredentials("001-2015", "001-2015"));
    }
}
