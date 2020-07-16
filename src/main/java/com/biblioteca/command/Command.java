package com.biblioteca.command;

import com.biblioteca.model.User;

public interface Command<T, U> {
    U performAction(T input, User user);
    String description();
}