package com.fabio.libary.controller;

import com.fabio.libary.model.book.Book;
import com.fabio.libary.model.person.AjaxBookListResponse;
import com.fabio.libary.model.person.OnLoanSearchCriteria;
import com.fabio.libary.model.person.Person;
import com.fabio.libary.service.BookService;
import com.fabio.libary.service.PersonService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static sun.plugin2.util.PojoUtil.toJson;

@RunWith(SpringRunner.class)
@WebMvcTest(PersonController.class)
public class PersonControllerTest {

    @Autowired
    MockMvc mvc;

    @MockBean
    BookService bookService;
    @MockBean
    PersonService personService;

    @Test
    public void testGetAllPeopleViewCall() throws Exception {
        List<Person> personList = new ArrayList<>();
        personList.add(new Person("1", "Test Person", "0412 3456 7890", "test@email.com"));

        when(personService.getAll()).thenReturn(personList);

        mvc.perform(get("/people"))
                .andExpect(view().name("showPeople"))
                .andExpect(model().attribute("people", personList));
    }

    @Test
    public void testOneBookOnLoanApiCall() throws Exception {
        List<Book> bookList = new ArrayList<>();
        bookList.add(new Book("1", "Test Book", "Test Author", "1234", 1));
        when(bookService.getBooksOnLoanForPerson("1")).thenReturn(bookList);

        OnLoanSearchCriteria successOnLoanSearchCriteria = new OnLoanSearchCriteria();
        successOnLoanSearchCriteria.setPersonId("1");

        AjaxBookListResponse ajaxBookListResponse = new AjaxBookListResponse();
        ajaxBookListResponse.setMessage(String.format("User has %d book(s) on loan", bookList.size()));
        ajaxBookListResponse.setResults(bookList);

        mvc.perform(post("/api/findOnLoan")
                .contentType(MediaType.APPLICATION_JSON)
                .content(toJson(successOnLoanSearchCriteria))
                .characterEncoding("UTF-8"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value(String.format("User has %d book(s) on loan", bookList.size())));
    }

    @Test
    public void testNoBooksOnLoanApiCall() throws Exception {
        List<Book> bookList = new ArrayList<>();
        when(bookService.getBooksOnLoanForPerson("1")).thenReturn(bookList);

        OnLoanSearchCriteria successOnLoanSearchCriteria = new OnLoanSearchCriteria();
        successOnLoanSearchCriteria.setPersonId("1");

        AjaxBookListResponse ajaxBookListResponse = new AjaxBookListResponse();
        ajaxBookListResponse.setMessage("User has no books on loan at the moment");
        ajaxBookListResponse.setResults(bookList);

        mvc.perform(post("/api/findOnLoan")
                .contentType(MediaType.APPLICATION_JSON)
                .content(toJson(successOnLoanSearchCriteria))
                .characterEncoding("UTF-8"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("User has no books on loan at the moment"));


    }
}
