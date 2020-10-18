package com.fabio.libary.service;

import com.fabio.libary.model.book.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService implements IBookService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Book> getAll() {

        String sql = "SELECT * FROM books";

        return jdbcTemplate.query(
                sql,
                (rs, rowNum) ->
                        new Book(
                                rs.getString("id"),
                                rs.getString("title"),
                                rs.getString("author"),
                                rs.getString("ISBN"),
                                rs.getInt("lent_person_id")
                        )
        );
    }

    @Override
    public List<Book> getBooksOnLoanForPerson(String personId) {
        String sql = "SELECT * FROM books WHERE lent_person_id = ?";

        return jdbcTemplate.query(
                sql,
                new String[]{personId},
                (rs, rowNum) ->
                        new Book(
                                rs.getString("id"),
                                rs.getString("title"),
                                rs.getString("author"),
                                rs.getString("ISBN"),
                                rs.getInt("lent_person_id")
                        )
        );
    }

}
