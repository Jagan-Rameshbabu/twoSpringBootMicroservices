package com.bytecode.jpaexample.SpringBootMySqlJpaRestExample;


import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class BookValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return Book.class.equals(clazz);
    }

    @Override
    public void validate(Object obj, Errors errors) {
        Book book = (Book) obj;
        if (checkInputString(book.getTitle())) {
            errors.rejectValue("title", "the title mustn't be empty!");
        }

        if (checkInputString(book.getArgument())) {
            errors.rejectValue("argument", "the argument mustn't be empty!");
        }

        if (isPositiveNumber(book.getArgument())) {
            errors.rejectValue("fk_author", "the author id must be a positive number!");
        }
    }

    private boolean isPositiveNumber(String input){
        if(input.contains("[a-zA-Z]+") == false)
            return true;
        return false;

    }

    private boolean checkInputString(String input) {
        return (input == null || input.trim().length() == 0);
    }
}