package com.biblioteca.servlet;

import com.biblioteca.Biblioteca;
import com.biblioteca.TokenService;
import com.biblioteca.command.CheckoutBook.Input;
import com.biblioteca.command.CheckoutBook.Output;
import com.biblioteca.model.User;
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

public class CheckoutBookServletTest {
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
        String authToken = "@abcd";
        String requestBody = "{ 'bookName' : 'Let us C' }";

        setUpMockRequest(authToken, requestBody);

        User user = mock(User.class);
        TokenService tokenService = mock(TokenService.class);
        stubTokenServiceGetUserFromToken(tokenService, authToken, user);

        Biblioteca biblioteca = mock(Biblioteca.class);
        Input input = new Input("Let us C");
        Output output = new Output("That book is not available");

        stubBibliotecaCheckoutBook(biblioteca, input, output, user);

        String responseBody = gson.toJson(gson.toJson(output));
        StringWriter stringWriter = setupMockResponse();

        CheckoutBookServlet checkoutBookServlet = getCheckoutBookServlet(biblioteca, tokenService);
        checkoutBookServlet.doPost(request, response);

        assertEquals(responseBody, stringWriter.toString());
    }

    @Test
    public void doPostAuthenticationFails() throws Exception {
        String authToken = "@1245";
        String requestBody = "";

        setUpMockRequest(authToken, requestBody);

        TokenService tokenService = mock(TokenService.class);
        Exception exception = new Exception("Token is not valid");
        stubTokenServiceGetUserFromToken(tokenService, authToken, exception);

        Biblioteca biblioteca = mock(Biblioteca.class);

        String responseBody = gson.toJson(gson.toJson(new Output(exception.getLocalizedMessage())));

        StringWriter stringWriter = setupMockResponse();

        CheckoutBookServlet checkoutBookServlet = getCheckoutBookServlet(biblioteca, tokenService);
        checkoutBookServlet.doPost(request, response);

        assertEquals(responseBody, stringWriter.toString());
    }

    private CheckoutBookServlet getCheckoutBookServlet(Biblioteca biblioteca, TokenService tokenService) {
        CheckoutBookServlet checkoutBookServlet = new CheckoutBookServlet();
        checkoutBookServlet.biblioteca = biblioteca;
        checkoutBookServlet.tokenService = tokenService;

        return checkoutBookServlet;
    }

    private void stubTokenServiceGetUserFromToken(TokenService tokenService, String token, User user) throws Exception {
        when(tokenService.getUserFromToken(token)).thenReturn(user);
    }

    private void stubTokenServiceGetUserFromToken(TokenService tokenService, String token, Exception exception) throws Exception {
        when(tokenService.getUserFromToken(token)).thenThrow(exception);
    }

    private void stubBibliotecaCheckoutBook(Biblioteca biblioteca, Input input, Output output, User user) {
        when(biblioteca.performCheckoutBook(input, user)).thenReturn(output);
    }

    private void setUpMockRequest(String authHeader, String data) throws IOException {
        BufferedReader bufferedReader = mock(BufferedReader.class);
        when(bufferedReader.readLine()).thenReturn(data);
        when(request.getReader()).thenReturn(bufferedReader);

        when(request.getHeader("Authorization")).thenReturn(authHeader);
    }

    private StringWriter setupMockResponse() throws IOException {
        StringWriter stringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(stringWriter);

        when(response.getWriter()).thenReturn(printWriter);

        return stringWriter;
    }
}