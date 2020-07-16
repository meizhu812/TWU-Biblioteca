package com.biblioteca;

import com.biblioteca.model.User;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;

public class TokenServiceTest {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    private TokenService tokenService;

    @Before
    public void setUp() {
        tokenService = TokenService.getInstance();
    }

    @Test
    public void maintainsTokenForUser() throws Exception {
        User expectedUser = mock(User.class);
        String token = tokenService.generateTokenForUser(expectedUser);

        User user = tokenService.getUserFromToken(token);

        assertEquals(expectedUser, user);
    }

    @Test
    public void throwsExceptionIfInvalidToken() throws Exception {
        String token = "randomToken";
        expectedException.expect(Exception.class);
        expectedException.expectMessage("Invalid Token");

        tokenService.getUserFromToken(token);
    }
}