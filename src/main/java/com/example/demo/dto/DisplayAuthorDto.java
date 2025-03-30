package com.example.demo.dto;

import com.example.demo.model.domain.Author;
import com.example.demo.model.domain.Country;

import java.util.List;
import java.util.stream.Collectors;

public record DisplayAuthorDto (
        String name,
        String surname,
        Long countryId){
    public static DisplayAuthorDto from(Author author) {
        return new DisplayAuthorDto(
                author.getName(),
                author.getSurname(),
                author.getCountry().getId()
        );
    }

    public static List<DisplayAuthorDto> from(List<Author> authors) {
        return authors.stream().map(DisplayAuthorDto::from).collect(Collectors.toList());
    }

    public Author toAuthor(Country country) {
        return new Author(name, surname, country);
    }

}

