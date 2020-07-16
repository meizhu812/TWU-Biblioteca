package com.biblioteca.command;

import com.biblioteca.model.Library;
import com.biblioteca.model.User;

import java.util.Objects;

public class CheckoutBook implements Command<CheckoutBook.Input, CheckoutBook.Output> {

    private final Library library;

    public CheckoutBook(Library library) {
        this.library = library;
    }

    @Override
    public Output performAction(Input input, User user) {
        String bookName = input.bookName;
        if(library.checkoutBook(bookName, user))
            return new Output("Thank you! Enjoy the book");
        else
            return new Output("That book is not available");
    }

    @Override
    public String description() {
        return "Checkout Book";
    }

    public static class Input {
        String bookName;

        public Input(String bookName) {
            this.bookName = bookName;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Input input = (Input) o;
            return Objects.equals(bookName, input.bookName);
        }
    }

    public static class Output {
        String message;

        public Output(String message) {
            this.message = message;
        }
    }

}