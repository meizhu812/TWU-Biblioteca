package com.biblioteca.servlet;

import com.biblioteca.Biblioteca;
import com.biblioteca.TokenService;
import com.biblioteca.command.Login.Input;
import com.biblioteca.command.Login.Output;
import com.biblioteca.model.User;
import com.biblioteca.servlet.LoginServlet.LoginOutput;
import com.google.gson.Gson;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class LoginServletTest {
    @Mock
    private HttpServletRequest request;
    @Mock
    private HttpServletResponse response;

    private Gson gson = new Gson();

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void doPost() throws Exception {
        String requestBody = "{ 'libraryNumber' : '123456', 'password': '@5678' }";
        setUpMockRequest(requestBody);

        Biblioteca biblioteca = mock(Biblioteca.class);
        Input input = new Input("123456", "@5678");

        User user = mock(User.class);
        Output output = new Output("Login Successful", user, new String[]{"Checkout Book"});

        stubBibliotecaCheckoutBook(biblioteca, input, output);

        TokenService tokenService = mock(TokenService.class);
        String token = "itsRandomString";
        stubTokenServicegenerateToken(tokenService, user, token);

        LoginOutput loginOutput = new LoginOutput(output.message, output.commandsDescription, token);
        String responseBody = gson.toJson(gson.toJson(loginOutput));
        StringWriter stringWriter = setupMockResponse();

        LoginServlet loginServlet = getLoginServlet(biblioteca, tokenService);
        loginServlet.doPost(request, response);

        assertEquals(responseBody, stringWriter.toString());
    }

    private LoginServlet getLoginServlet(Biblioteca biblioteca, TokenService tokenService) {
        LoginServlet loginServlet = new LoginServlet();
        loginServlet.biblioteca = biblioteca;
        loginServlet.tokenService = tokenService;

        return loginServlet;
    }

    private void stubTokenServicegenerateToken(TokenService tokenService, User user, String token) {
        when(tokenService.generateTokenForUser(user)).thenReturn(token);
    }

    private void stubBibliotecaCheckoutBook(Biblioteca biblioteca, Input input, Output output) {
        when(biblioteca.performLogin(input)).thenReturn(output);
    }

    private void setUpMockRequest(String data) throws IOException {
        BufferedReader bufferedReader = mock(BufferedReader.class);
        when(bufferedReader.readLine()).thenReturn(data);

        when(request.getReader()).thenReturn(bufferedReader);
    }

    private StringWriter setupMockResponse() throws IOException {
        StringWriter stringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(stringWriter);

        when(response.getWriter()).thenReturn(printWriter);

        return stringWriter;
    }
}