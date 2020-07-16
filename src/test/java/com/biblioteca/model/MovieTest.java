package com.biblioteca.model;

import org.junit.Test;

import static org.junit.Assert.assertEquals;


public class MovieTest {

    @Test
    public void twoMoviesAreSameIfThereNamesAreSame() {
        Movie movie1 = new Movie("ABCD2", "2015", "PrabhuDeva", 8);
        Movie movie2 = new Movie("ABCD2", "2015", "PrabhuDeva", 8);

        assertEquals(true, movie1.hasTitle(movie2.name()));
    }
}
