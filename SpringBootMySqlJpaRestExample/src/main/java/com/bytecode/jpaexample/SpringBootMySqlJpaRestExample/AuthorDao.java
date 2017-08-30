package com.bytecode.jpaexample.SpringBootMySqlJpaRestExample;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorDao extends JpaRepository<Author, Integer>{
    //it's empty because uses the methods of JpaRepository without overwriting them
    //it will be Spring which will generate its concrete implementation.
    //we won't never see this implementation but we'll refer always to this interface (see in Service Layer)
}
