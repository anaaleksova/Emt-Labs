package com.example.demo.service.application;

import com.example.demo.dto.CreateBookDto;
import com.example.demo.dto.DisplayBookDto;
import com.example.demo.model.domain.Book;

import java.util.List;
import java.util.Optional;

public interface BookApplicationService {
    Optional<DisplayBookDto> update(Long id, CreateBookDto bookDto) ;

    Optional<DisplayBookDto> save(CreateBookDto bookDto);

    Optional<DisplayBookDto> findById(Long id);

    List<DisplayBookDto> findAll();

    void deleteById(Long id);
    Optional<DisplayBookDto> markAsRented(Long id);
}
