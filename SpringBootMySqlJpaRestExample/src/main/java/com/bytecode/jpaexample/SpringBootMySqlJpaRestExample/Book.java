package com.bytecode.jpaexample.SpringBootMySqlJpaRestExample;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@Table(name="books")
public class Book implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", insertable = false, updatable = false)
    private int id;

    @NotNull @NotBlank @NotEmpty    //for the javax.validation
    @Column(name = "title")         //for Spring Data JPA to map the real table in DB
    private String title;

    @NotNull @NotBlank @NotEmpty    //for the javax.validation
    @Column(name = "argument")      //for Spring Data JPA to map the real table in DB
    private String argument;

    //   @ManyToOne(fetch = FetchType.LAZY)
    //   @JoinColumn(name = "authors.id")
    @Column(name = "fk_author")
    private int fk_author;

    /* ---- BOTH THE CONSTRUCTORS ---- */

    public Book(){}

    public Book(int id, String title, String argument, int fk_author) {
        this.id = id;
        this.title = title;
        this.argument = argument;
        this.fk_author = fk_author;
    }

    /* ---- GETTERS AND SETTERS ---- */

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    @Override
    public String toString(){
        return "{\"title\":\"" + title + "\", \"argument\":\"" + argument + "\", \"fk_author: " + fk_author + "}";
    }
}
