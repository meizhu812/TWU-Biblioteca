package com.biblioteca.command;

import com.biblioteca.model.Authenticator;
import com.biblioteca.model.User;

import java.util.Objects;

public class Login implements Command<Login.Input, Login.Output> {

    private final Authenticator authenticator;
    private final CheckoutBook checkoutBook;

    public Login(Authenticator authenticator, CheckoutBook checkoutBook) {
        this.authenticator = authenticator;
        this.checkoutBook = checkoutBook;
    }

    @Override
    public Output performAction(Input input, User user) {
        String libraryNumber = input.libraryNumber;
        String password = input.password;
        user = authenticator.authenticate(libraryNumber, password);
        if(user == null) {
            return new Output("Login unsuccessful");
        }
        else {
            String[] commandsDescriptions = new String[] {checkoutBook.description()};
            return new Output("Login successful", user, commandsDescriptions);
        }
    }

    @Override
    public String description() {
        return "Login";
    }

    public static class Input {
        String libraryNumber;
        String password;

        public Input(String libraryNumber, String password) {
            this.libraryNumber = libraryNumber;
            this.password = password;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Input input = (Input) o;
            return Objects.equals(libraryNumber, input.libraryNumber) &&
                    Objects.equals(password, input.password);
        }

        @Override
        public int hashCode() {

            return Objects.hash(libraryNumber, password);
        }
    }

    public static class Output {
        final public String message;
        final public  User user;
        final public String[] commandsDescription;

        public Output(String message) {
            this.message = message;
            this.user = null;
            this.commandsDescription = null;
        }

        public Output(String message, User user, String[] commandsDescription) {
            this.message = message;
            this.user = user;
            this.commandsDescription = commandsDescription;
        }
    }
}
