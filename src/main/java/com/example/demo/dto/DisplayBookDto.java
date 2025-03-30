package com.example.demo.dto;

import com.example.demo.model.domain.Author;
import com.example.demo.model.domain.Book;
import com.example.demo.model.domain.Country;
import com.example.demo.model.enumerations.Category;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public record DisplayBookDto(String name,
                             Category category,
                             Long authorId,
                             Integer availableCopies) {
    public static DisplayBookDto from(Book book) {
        return new DisplayBookDto(
                book.getName(),
                book.getCategory(),
                book.getAuthor().getId(),
                book.getAvailableCopies()
        );
    }

    public static List<DisplayBookDto> from(List<Book> books) {
        return books.stream().map(DisplayBookDto::from).collect(Collectors.toList());
    }


    public Book toBook(Author author) {
        return new Book(name,category,author,availableCopies);
    }
}
