package com.bytecode.example.SpringBootConsumingRestPlusSecurity;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

@Controller
public class MyController {

    @RequestMapping("/get/books")
    @ResponseBody
    public ResponseEntity<Book[]> getAllBooks(){
        RestTemplate template = new RestTemplate();
        ResponseEntity<Book[]> responseEntity = template.getForEntity("http://localhost:8080/books", Book[].class);;
        return responseEntity;
    }

    @RequestMapping("/get/first/book")
    @ResponseBody
    public Book getFirstBook(){
        RestTemplate template = new RestTemplate();
        ResponseEntity<Book[]> responseEntity = template.getForEntity("http://localhost:8080/books", Book[].class);;
        Book[] books = responseEntity.getBody();
        return books[0];
    }

}
