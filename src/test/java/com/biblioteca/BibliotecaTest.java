package com.biblioteca;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

public class BibliotecaTest {
    @Test
    public void shouldReturnAndInstanceOfBibliotecaClass() {
        Biblioteca biblioteca = Biblioteca.getInstance();

        assertThat(biblioteca, is(not(nullValue())));
    }


}