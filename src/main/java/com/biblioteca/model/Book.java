package com.biblioteca.model;

public class Book extends Item {
    private String author;
    private String yearOfPublishing;

    public Book(String name, String author, String yearOfPublishing) {
        super(name);
        this.author = author;
        this.yearOfPublishing = yearOfPublishing;
    }

    @Override
    public String toString() {
        return name + '|' +
                author + '|' +
                yearOfPublishing + '|';
    }
}