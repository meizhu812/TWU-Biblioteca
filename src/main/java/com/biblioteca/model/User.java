package com.biblioteca.model;

import java.util.Objects;

public class User {
    private String libraryNumber;
    private String password;
    private String name;
    private String emailAddress;
    private String mobile;
    private boolean librarian;

    public User(String libraryNumber, String password, String name, String emailAddress, String mobile, boolean librarian) {
        this.libraryNumber = libraryNumber;
        this.password = password;
        this.name = name;
        this.emailAddress = emailAddress;
        this.mobile = mobile;
        this.librarian = librarian;
    }

    String getLibraryNumber() {
        return libraryNumber;
    }

    public boolean hasLoginCredentials(String libraryNumber, String password) {
        return this.libraryNumber.equals(libraryNumber) && this.password.equals(password);
    }

    @Override
    public String toString() {
        return "User{" +
                " name='" + name + '\'' +
                ", emailAddress='" + emailAddress + '\'' +
                ", mobile='" + mobile + '\'' +
                '}' + '\n';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return librarian == user.librarian &&
                Objects.equals(libraryNumber, user.libraryNumber) &&
                Objects.equals(password, user.password) &&
                Objects.equals(name, user.name) &&
                Objects.equals(emailAddress, user.emailAddress) &&
                Objects.equals(mobile, user.mobile);
    }

    @Override
    public int hashCode() {
        return Objects.hash(libraryNumber, password, name, emailAddress, mobile, librarian);
    }
}