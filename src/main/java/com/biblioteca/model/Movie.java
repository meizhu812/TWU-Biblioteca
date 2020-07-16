package com.biblioteca.model;

public class Movie extends Item {

    private String year;
    private String director;
    private int movieRating;

    public Movie(String name, String year, String director, int movieRating) {
        super(name);
        this.year = year;
        this.director = director;
        this.movieRating = movieRating;
    }

    @Override
    public String toString() {
        return name + '|' +
                year + '|' +
                director + '|' +
                movieRating + '|';
    }
}