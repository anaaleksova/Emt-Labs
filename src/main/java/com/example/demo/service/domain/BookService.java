package com.example.demo.service.domain;

import com.example.demo.model.domain.Book;

import java.util.List;
import java.util.Optional;

public interface BookService {
    List<Book> findAll();

    Optional<Book> save(Book book);

    Optional<Book> findById(Long id);

    Optional<Book> update(Long id, Book book);

    Optional<Book> markAsRented(Long id);

    void deleteById(Long id);

}
