package com.example.demo.dto;

import com.example.demo.model.domain.Author;
import com.example.demo.model.domain.Book;
import com.example.demo.model.enumerations.Category;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ManyToOne;

import java.util.List;
import java.util.stream.Collectors;

public record CreateBookDto(
        String name,
        Category category,
        Long authorId,
        Integer availableCopies
) {

    public static CreateBookDto from(Book book) {
        return new CreateBookDto(
                book.getName(),
                book.getCategory(),
                book.getAuthor().getId(),
                book.getAvailableCopies()
        );
    }

    public static List<CreateBookDto> from(List<Book> books) {
        return books.stream().map(CreateBookDto::from).collect(Collectors.toList());
    }

    public Book toBook(Author author) {
        return new Book(name,category,author,availableCopies);
    }

}

