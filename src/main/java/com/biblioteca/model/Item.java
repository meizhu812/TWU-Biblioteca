package com.biblioteca.model;

public abstract class Item {
    protected String name;

    Item(String name) {
        this.name = name;
    }

    public boolean hasTitle(String thatName) {
        return name.equals(thatName);
    }

    public String name() {
        return name;
    }
}
