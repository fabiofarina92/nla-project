package com.fabio.libary.model.book;

public class Book {

    private String id;
    private String title;
    private String author;
    private String ISBN;
    private Integer lentPersonId;

    public Book() {
    }

    public Book(String id, String title, String author, String ISBN, Integer lentPersonId) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.ISBN = ISBN;
        this.lentPersonId = lentPersonId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public Integer getLentPersonId() {
        return lentPersonId;
    }

    public void setLentPersonId(Integer lentPersonId) {
        this.lentPersonId = lentPersonId;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", ISBN='" + ISBN + '\'' +
                ", lentPersonId=" + lentPersonId +
                '}';
    }
}
