package com.fabio.libary.controller;

import com.fabio.libary.model.book.AjaxPersonResponse;
import com.fabio.libary.model.book.Book;
import com.fabio.libary.model.book.PersonLoanedSearchCriteria;
import com.fabio.libary.model.person.Person;
import com.fabio.libary.service.BookService;
import com.fabio.libary.service.PersonService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(BookController.class)
public class BookControllerTest {

    @Autowired
    MockMvc mvc;

    @MockBean BookService bookService;
    @MockBean PersonService personService;

    @Test
    public void testGetAllBooksViewCall() throws Exception {
        List<Book> bookList = new ArrayList<>();
        bookList.add(new Book("1", "Test Book", "Test Author", "1234", 1));
        when(bookService.getAll()).thenReturn(bookList);

        mvc.perform(get("/books"))
                .andExpect(view().name("showBooks"))
                .andExpect(model().attribute("books", bookList));

    }

    @Test
    public void testBookOnLoanApiCall() throws Exception {

        ObjectMapper mapper = new ObjectMapper();
        Person person = new Person("1", "Test Person", "0412 3456 7890", "test@email.com");
        when(personService.getPersonById("1")).thenReturn(person);


        PersonLoanedSearchCriteria successPersonSearchCriteria = new PersonLoanedSearchCriteria();
        successPersonSearchCriteria.setPersonId("1");

        AjaxPersonResponse ajaxPersonResponse = new AjaxPersonResponse();
        ajaxPersonResponse.setMessage("Book is currently on loan");
        ajaxPersonResponse.setResult(person);

        mvc.perform(post("/api/findPersonLoaned")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(successPersonSearchCriteria)))
                .andExpect(content().json(mapper.writeValueAsString(ajaxPersonResponse)));


    }

    @Test
    public void testBookAvailableApiCall() throws Exception {

        ObjectMapper mapper = new ObjectMapper();
        when(personService.getPersonById(null)).thenThrow(EmptyResultDataAccessException.class);


        PersonLoanedSearchCriteria successPersonSearchCriteria = new PersonLoanedSearchCriteria();
        successPersonSearchCriteria.setPersonId(null);

        AjaxPersonResponse ajaxPersonResponse = new AjaxPersonResponse();
        ajaxPersonResponse.setMessage("Book is not loaned to anyone");
        ajaxPersonResponse.setResult(null);

        PersonLoanedSearchCriteria failPersonSearchCriteria = new PersonLoanedSearchCriteria();
        mvc.perform(post("/api/findPersonLoaned")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(successPersonSearchCriteria)))
                .andExpect(content().json(mapper.writeValueAsString(ajaxPersonResponse)));

    }
}
