package com.fabio.libary.model.person;

import com.fabio.libary.model.book.Book;

import java.util.List;

public class AjaxBookListResponse {

    String message;
    List<Book> results;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<Book> getResults() {
        return results;
    }

    public void setResults(List<Book> results) {
        this.results = results;
    }

    @Override
    public String toString() {
        return "AjaxBookListResponse{" +
                "message='" + message + '\'' +
                ", results=" + results +
                '}';
    }
}
