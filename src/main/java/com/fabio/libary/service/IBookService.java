package com.fabio.libary.service;

import com.fabio.libary.model.book.Book;

import java.util.List;

public interface IBookService {

    List<Book> getAll();

    List<Book> getBooksOnLoanForPerson(String personId);
}
