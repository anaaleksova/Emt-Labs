package com.example.demo.service.application.impl;

import com.example.demo.dto.CreateAuthorDto;
import com.example.demo.dto.DisplayAuthorDto;
import com.example.demo.dto.DisplayBookDto;
import com.example.demo.model.domain.Author;
import com.example.demo.model.domain.Country;
import com.example.demo.service.application.AuthorApplicationService;
import com.example.demo.service.domain.AuthorService;
import com.example.demo.service.domain.CountryService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorApplicationServiceImpl implements AuthorApplicationService {

    private final AuthorService authorService;
    private final CountryService countryService;

    public AuthorApplicationServiceImpl(AuthorService authorService, CountryService countryService) {
        this.authorService = authorService;
        this.countryService = countryService;
    }

    @Override
    public Optional<DisplayAuthorDto> update(Long id, CreateAuthorDto authorDto) {
        Optional<Country> country = countryService.findById(authorDto.countryId());

        return authorService.update(id,authorDto.toAuthor(country.orElse(null))).map(DisplayAuthorDto::from);
    }

    @Override
    public Optional<DisplayAuthorDto> save(CreateAuthorDto authorDto) {
        Optional<Country> country = countryService.findById(authorDto.countryId());

        if(country.isPresent())
        {
            return authorService.save(authorDto.toAuthor(country.get())).map(DisplayAuthorDto::from);
        }
        return Optional.empty();
    }

    @Override
    public Optional<DisplayAuthorDto> findById(Long id) {
        return authorService.findById(id).map(DisplayAuthorDto::from);
    }

    @Override
    public List<DisplayAuthorDto> findAll() {
        return authorService.findAll().stream().map(DisplayAuthorDto::from).toList();
    }

    @Override
    public void deleteById(Long id) {
        authorService.deleteById(id);
    }
}
