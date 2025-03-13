package com.example.demo.service;

import com.example.demo.model.Book;
import com.example.demo.model.BookDto;

import java.util.List;
import java.util.Optional;

public interface BookService {
    List<Book> findAll();

    Optional<Book> save(BookDto book);

    Optional<Book> findById(Long id);

    Optional<Book> update(Long id, BookDto book);

    Optional<Book> markAsRented(Long id);

    void deleteById(Long id);

}
