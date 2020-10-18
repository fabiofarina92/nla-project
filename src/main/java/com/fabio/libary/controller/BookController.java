package com.fabio.libary.controller;

import com.fabio.libary.model.book.AjaxPersonResponse;
import com.fabio.libary.model.book.Book;
import com.fabio.libary.model.book.PersonLoanedSearchCriteria;
import com.fabio.libary.model.person.Person;
import com.fabio.libary.service.IBookService;
import com.fabio.libary.service.IPersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
public class BookController {

    @Autowired
    private IBookService bookService;

    @Autowired
    private IPersonService personService;

    @GetMapping(value = "/books")
    public ModelAndView showBooks() {
        List<Book> books = bookService.getAll();
        Map<String, Object> params = new HashMap<>();
        params.put("books", books);

        return new ModelAndView("showBooks", params);
    }

    @PostMapping("/api/findPersonLoaned")
    public ResponseEntity<?> findPersonLoaned(@RequestBody PersonLoanedSearchCriteria personLoanedSearchCriteria, Errors errors) {
        AjaxPersonResponse response = new AjaxPersonResponse();

        if (errors.hasErrors()) {
            response.setMessage(errors.getAllErrors()
                    .stream().map(DefaultMessageSourceResolvable::getDefaultMessage)
                    .collect(Collectors.joining(",")));
            return ResponseEntity.badRequest().body(response);
        }

        Person personBookLoaned;
        try {
            personBookLoaned = personService.getPersonById(personLoanedSearchCriteria.getPersonId());
            response.setMessage("Book is currently on loan");
        } catch (EmptyResultDataAccessException emptyResultDataAccessException) {
            personBookLoaned = null;
            response.setMessage("Book is not loaned to anyone");
        }

        response.setResult(personBookLoaned);

        return ResponseEntity.ok(response);
    }

}
