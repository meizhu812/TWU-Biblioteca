package com.biblioteca.servlet;

import com.biblioteca.command.Login;
import com.biblioteca.command.Login.Input;
import com.biblioteca.command.Login.Output;
import com.biblioteca.model.User;
import com.google.gson.JsonSyntaxException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet(name = "Login", urlPatterns = {"login"}, loadOnStartup = 1)
public class LoginServlet extends BaseServlet<Input, Login.Output> {

    @Override
    public void init() throws ServletException {
        super.init();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException, JsonSyntaxException {
        Input input = getInput(request, Input.class);
        Output output = performCommand(input, null);

        User user = output.user;
        String token;
        if(user != null) {
            token = tokenService.generateTokenForUser(user);
        } else {
            token = null;
        }

        LoginOutput loginOutput = new LoginOutput(output.message, output.commandsDescription, token);

        setOutput(loginOutput, response);
    }

    @Override
    protected Output performCommand(Input input, User user) {
        return biblioteca.performLogin(input);
    }

    static class LoginOutput {
        private final String message;
        private  final String[] commandsDescription;
        private final String token;

        LoginOutput(String message, String[] commandsDescription, String token) {
            this.message = message;
            this.commandsDescription = commandsDescription;
            this.token = token;
        }
    }
}