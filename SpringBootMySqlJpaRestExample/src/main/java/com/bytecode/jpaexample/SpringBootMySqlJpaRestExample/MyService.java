package com.bytecode.jpaexample.SpringBootMySqlJpaRestExample;

import java.util.List;

public interface MyService {

    List<Book> getBooks();

    List<Author> getAuthors();

    List<Book> getBookByAuthorName(String name);

    Author saveAuthor(Author author);

    Book saveBook(Book book);

}
