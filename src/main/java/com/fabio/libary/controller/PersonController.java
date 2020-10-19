package com.fabio.libary.controller;

import com.fabio.libary.model.book.Book;
import com.fabio.libary.model.person.AjaxBookListResponse;
import com.fabio.libary.model.person.OnLoanSearchCriteria;
import com.fabio.libary.model.person.Person;
import com.fabio.libary.service.IBookService;
import com.fabio.libary.service.IPersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.DefaultMessageSourceResolvable;
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
public class PersonController {

    @Autowired
    private IPersonService personService;

    @Autowired
    private IBookService bookService;

    @GetMapping(value = "/people")
    public ModelAndView showPeople() {

        List<Person> people = personService.getAll();
        Map<String, Object> params = new HashMap<>();
        params.put("people", people);

        return new ModelAndView("showPeople", params);
    }

    @PostMapping("/api/findOnLoan")
    public ResponseEntity<?> getBooksOnLoan(@RequestBody OnLoanSearchCriteria onLoanSearchCriteria, Errors errors) {
        AjaxBookListResponse response = new AjaxBookListResponse();

        if (errors.hasErrors()) {
            response.setMessage(errors.getAllErrors()
                    .stream().map(DefaultMessageSourceResolvable::getDefaultMessage)
                    .collect(Collectors.joining(",")));
            return ResponseEntity.badRequest().body(response);
        }

        List<Book> booksOnLoan = bookService.getBooksOnLoanForPerson(onLoanSearchCriteria.getPersonId());

        if (booksOnLoan.isEmpty()) {
            response.setMessage("User has no books on loan at the moment");
        } else {
            response.setMessage(String.format("User has %d book(s) on loan", booksOnLoan.size()));
        }
        response.setResults(booksOnLoan);

        return ResponseEntity.ok(response);
    }
}
