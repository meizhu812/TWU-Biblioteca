package com.biblioteca.servlet;

import com.biblioteca.Biblioteca;
import com.biblioteca.TokenService;
import com.biblioteca.model.User;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

abstract class BaseServlet<Input, Output> extends HttpServlet {

    TokenService tokenService;
    Biblioteca biblioteca;
    Gson gson = new Gson();

    @Override
    public void init() throws ServletException {
        super.init();
        biblioteca = Biblioteca.getInstance();
        tokenService = TokenService.getInstance();
    }

    abstract protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException, JsonSyntaxException;

    protected abstract Output performCommand(Input input, User user);

    User authenticateUser(HttpServletRequest request) throws Exception {
        String token = request.getHeader("Authorization");
        return tokenService.getUserFromToken(token);
    }

    Input getInput(HttpServletRequest request, Class<Input> inputClass) throws IOException, JsonSyntaxException {
        String requestBody = getRequestBody(request);
        return createInputFromRequestBody(requestBody, inputClass);
    }

    <APIOutput> void setOutput(APIOutput output, HttpServletResponse response) throws IOException {
        String jsonResponse = gson.toJson(output);
        setResponse(gson.toJson(jsonResponse), response);
    }

    private Input createInputFromRequestBody(String requestBody, Class<Input> inputClass) {
        return gson.fromJson(requestBody, inputClass);
    }

    private String getRequestBody(HttpServletRequest request) throws IOException {
        return request.getReader().readLine();
    }

    private void setResponse(String jsonResponse, HttpServletResponse response) throws IOException {
        response.getWriter().print(jsonResponse);
    }
}
