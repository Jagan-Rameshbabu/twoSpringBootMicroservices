package com.bytecode.jpaexample.SpringBootMySqlJpaRestExample;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Concrete implementation of MyService.
 * It's annotated with @Service because it's a bean which will be injected by Spring
 * we'll refer to its Interface (MyService) even when it will be injected into the Controller class.
 */

@Service
@Transactional
public class MyServiceImpl implements MyService{

    @Autowired
    AuthorDao authorDao;

    @Autowired
    BookDao bookDao;

    @Override
    public List<Book> getBooks(){
        return bookDao.findAll();
    }

    @Override
    public List<Author> getAuthors(){
        return authorDao.findAll();
    }

    @Override
    public List<Book> getBookByAuthorName(String name){
        return bookDao.findBooksByAuthorName(name);
    }

    @Override
    public Author saveAuthor(Author author){
        return authorDao.save(author);
    }

    @Override
    public Book saveBook(Book book){
        return bookDao.save(book);
    }

}
