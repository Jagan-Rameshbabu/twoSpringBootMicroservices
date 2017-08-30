package com.bytecode.jpaexample.SpringBootMySqlJpaRestExample;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BookDao extends JpaRepository<Book, Integer>{

    // Spring will create the concrete implemetation of this class.
    // we'll always refer to this interface (see in Service Layer)

    /*
    This query method is created ad hoc because it's not among the ones in JpaRepository
    and it's not automatically generable using the "Method Name Strategy".
    As you can see it can freely operate on both the tables.
     */
    @Query(value = "SELECT * FROM books where books.fk_author = (SELECT id FROM authors WHERE authors.name = :authorName)",
            nativeQuery=true)
    public List<Book> findBooksByAuthorName(@Param("authorName") String authorName);

}
