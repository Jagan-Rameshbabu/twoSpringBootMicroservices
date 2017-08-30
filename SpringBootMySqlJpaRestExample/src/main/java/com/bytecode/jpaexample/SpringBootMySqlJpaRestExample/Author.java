package com.bytecode.jpaexample.SpringBootMySqlJpaRestExample;


import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@Table(name = "authors")
public class Author implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    //   @OneToMany(fetch = FetchType.LAZY)
    private int id;

    @NotNull @NotBlank @NotEmpty    //for the javax.validation
    @Column(name = "name")          //for Spring Data JPA to map the real table in DB
    private String name;

    @NotNull @NotBlank @NotEmpty    //for the javax.validation
    @Column(name = "surname")       //for Spring Data JPA to map the real table in DB
    private String surname;

    @NotNull @NotBlank @NotEmpty    //for the javax.validation
    @Column(name = "email")         //for Spring Data JPA to map the real table in DB
    private String email;


    /* ---- BOTH THE CONSTRUCTORS ---- */

    public Author(){}

    public Author(int id, String name, String surname, String email) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.email = email;
    }

    /* ---- GETTERS AND SETTERS ---- */

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString(){
        return "{\"name\":\"" + name + "\", \"surname\":\"" + surname + "\", \"email:\" " + email + "\"}";
    }
}
