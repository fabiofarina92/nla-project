package com.fabio.libary.model.book;

import com.fabio.libary.model.person.Person;


public class AjaxPersonResponse {

    String message;
    Person result;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Person getResult() {
        return result;
    }

    public void setResult(Person result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return "AjaxPersonResponse{" +
                "message='" + message + '\'' +
                ", result=" + result +
                '}';
    }
}
