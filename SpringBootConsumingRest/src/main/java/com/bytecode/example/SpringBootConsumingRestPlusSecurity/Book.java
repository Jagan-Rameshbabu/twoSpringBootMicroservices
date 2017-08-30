package com.bytecode.example.SpringBootConsumingRestPlusSecurity;


public class Book {

    private String title;
    private String argument;
    private int fk_author;

    public Book() {
    }

    public Book(String title, String argument, int fk_author) {
        this.title = title;
        this.argument = argument;
        this.fk_author = fk_author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getArgument() {
        return argument;
    }

    public void setArgument(String argument) {
        this.argument = argument;
    }

    public int getFk_author() {
        return fk_author;
    }

    public void setFk_author(int fk_author) {
        this.fk_author = fk_author;
    }
}
