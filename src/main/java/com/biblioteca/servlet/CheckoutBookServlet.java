package com.biblioteca.servlet;

import com.biblioteca.command.CheckoutBook.Output;
import com.biblioteca.model.User;
import com.google.gson.JsonSyntaxException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.biblioteca.command.CheckoutBook.Input;

@WebServlet(name = "CheckoutBook", urlPatterns = {"checkoutBook"}, loadOnStartup = 1)
public class CheckoutBookServlet extends BaseServlet<Input, Output> {

    @Override
    public void init() throws ServletException {
        super.init();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException, JsonSyntaxException {
        try {
            User user = authenticateUser(request);

            Input input = getInput(request, Input.class);
            Output output = performCommand(input, user);
            setOutput(output, response);
        } catch (Exception e) {
            setOutput(new Output(e.getLocalizedMessage()), response);
        }
    }

    @Override
    protected Output performCommand(Input input, User user) {
        return biblioteca.performCheckoutBook(input, user);
    }
}