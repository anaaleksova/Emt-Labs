package com.example.demo.dto;

import com.example.demo.model.domain.Author;
import com.example.demo.model.domain.Book;
import com.example.demo.model.domain.Country;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

public record CreateAuthorDto ( String name,
        String surname,
        Long countryId){

    public static CreateAuthorDto from(Author author) {
        return new CreateAuthorDto(
                author.getName(),
                author.getSurname(),
                author.getCountry().getId()
        );
    }

    public static List<CreateAuthorDto> from(List<Author> products) {
        return products.stream().map(CreateAuthorDto::from).collect(Collectors.toList());
    }
    public Author toAuthor(Country country) {
        return new Author(name, surname, country);
    }

}
