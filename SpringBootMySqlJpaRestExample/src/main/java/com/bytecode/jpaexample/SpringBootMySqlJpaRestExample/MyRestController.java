package com.bytecode.jpaexample.SpringBootMySqlJpaRestExample;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;


@Controller
public class MyRestController {

    @Autowired
    MyService myService;       //Spring will implement and inject its concrete class annotated with @Service

    @Autowired
    BookValidator bookValidator;

    @RequestMapping("/books")
    @ResponseBody
    public List<Book> getAllBooks(){
        return myService.getBooks();
    }

    @RequestMapping("/authors")
    @ResponseBody
    public List<Author> getAllAuthors(){
        return myService.getAuthors();
    }

    @RequestMapping("/books/of/{authorName}")
    @ResponseBody
    public List<Book> getBookByTheNameOfItsAuthor(@PathVariable String authorName){
        return myService.getBookByAuthorName(authorName);
    }


    /*
    this method implements the automatic binding from the HTML form (without form:tag of JSP)
    and implements JSR303 validation (non Spring). This is possible:
     - annotating the Entity Author with specific annotations @NotBlank, @Min(),@Max, @Range, etc.)
     - inserting @Valid before the parameter
     - adding BindingResult as parameter
     */

    @RequestMapping("/add/author")
    @ResponseBody
    public String addAuthor(@Valid Author bindedAuthor, BindingResult result){
        if (result.hasErrors()) {  //automatic JSR303 validation
            return "ERROR!";
        }

        Author addedAuthor = myService.saveAuthor(bindedAuthor);
        return "Author added correctly: " +  addedAuthor.toString();
    }


    /*
    if there wasn't BindingResult it would be a perfect case of automatic binding from a form into an Entity.
    This thanks to the fact that the inputs names properties into the HTML have the same name of the Entity fields.
    Adding the BindingResults, in this case we're making a validation using a SpringValidator.
     */
    @RequestMapping("/add/book")
    @ResponseBody
    public String addBook(Book bindedBook, BindingResult bindingResult){
        /* Spring validation */
        BookValidator bookValidator = new BookValidator();
        bookValidator.validate(bindedBook, bindingResult);

        if(bindingResult.hasErrors()){
            FieldError fieldError = bindingResult.getFieldError();
            return "Code: " + fieldError.getCode() + ", field:" +fieldError.getField();
        }
        /* end of Spring validation */
        Book addedBook = myService.saveBook(bindedBook);
        return "Book added correctly: " + addedBook.toString();
    }



    /*
    classic Hello World with Thymeleaf (thanks to pom.xml dependencies and only within resources/templates)
    No html or jsp pages is possible to forward Http requests this way!
     */
    @RequestMapping("/greeting")
    public String greeting(@RequestParam(value="name", required=false, defaultValue="World") String name, Model model) {
        model.addAttribute("name", name);
        return "greeting";
    }

    /*
    returning an HTML page with @ResponseBody: what a strange possibility!
     */
    @RequestMapping("/add")
    @ResponseBody
    public String goToAddPage(){
        return HTMLADDPAGE;
    }

    /*
    returning a thymeleaf page in templates. Only because the pom.xml is configured with two dependencies. No jsp, even no html
    page is possible to be opened with this way
     */
    @RequestMapping("/addPage")
    public String goToAddPage2(){
        return "addPage";
    }

    /*
        void which uses traditional JavaEE forwarding. Only to thymeleaf page for templates folder. Toward anything if in static folder!
     */
    @RequestMapping("/test")
    public void testPage(HttpServletRequest request, HttpServletResponse response) throws javax.servlet.ServletException, java.io.IOException{
        request.getRequestDispatcher("hellodear.html").forward(request, response);
    }


    final String HTMLADDPAGE =
            "<!DOCTYPE html>\n" +
                    "<html lang='en'>\n" +
                    "<head>\n" +
                    "    <meta charset='UTF-8'>\n" +
                    "    <title>Add PAGE</title>\n" +
                    "</head>\n" +
                    "<body>\n" +
                    "<h3> Add an Author </h3>\n" +
                    "<form action='/add/author' method='post' id='form1'>\n" +
                    "    Name: <input type='text' name='name'><br>\n" +
                    "    Surname: <input type='text' name='surname'><br>\n" +
                    "    Email: <input type='email' name='email'><br>\n" +
                    "</form>\n" +
                    "\n" +
                    "<button type='submit' form='form1' value='Submit1'>Add author</button>\n" +
                    "\n" +
                    "<h3> Add a Book </h3>\n" +
                    "<form action='/add/book' method='post' id='form2'>\n" +
                    "    Title: <input type='text' name='title'><br>\n" +
                    "    Argument: <input type='text' name='argument'><br>\n" +
                    "    Author index: <input type='number' name='fk_author'><br>\n" +
                    "</form>\n" +
                    "\n" +
                    "<button type='submit' form='form2' value='Submit2'>Add book</button>\n" +
                    "\n" +
                    "</body>\n" +
                    "</html>";


    @InitBinder("bindedBook")
    protected void initBinder(WebDataBinder binder) {
        binder.setValidator(new BookValidator());
    }


}

/*
You can use thymeleaf pages but you have to:
- insert the relative two dependencies in pom.xml
- insert the pages into resources>templates
- respect all the closing tags and the thymeleaf rules
- the you can get the page from controller returning a String with the name of the page without the extension.
 */


/*
BINDING FORMS GOLD RULE:
You can bind from a HTML form without using the form: tag of the jsp.
But you have to set the 'name' attributes of the input form equals to the fields of the Entity that needs to be mapped.

    @RequestMapping("/add/book")
    @ResponseBody
    public String addBook(Book bindedBook){              //automatic bind from form
        Book addedBook = myService.saveBook(bindedBook);
        return "Book added correctly: " + addedBook.toString();
    }

 */


/*
ADD VALIDATION TO YOU BINDED ENTITY
You can validate automatically an HTML Input form via server without creating a Validator:
you need to annotate Entities with specific annotations (@NotBlank, @Min(), @Range() etc. )
and also you need to pass to the controller the Entity to bind annotated with @Valid and Binding results

    @RequestMapping("/add/author")
    @ResponseBody
    public String addAuthor(@Valid Author bindedAuthor, BindingResult result){
        if (result.hasErrors()) {  //automatic validation
            return "ERROR!";
        }
        Author addedAuthor = myService.saveAuthor(bindedAuthor);
        return "Author added correctly: " +  addedAuthor.toString();
    }
*/


/*
You can use @Controller instead of @RestController, so you have more freedom
because if you want to get a JSON you can annotate the method with @ResponseBody
and return the Java Entity that will be transformed in JSON by JSONBinding library,
if you want to get a Page (thymeleaf or json) you don't use that annotation and return a String
*/