package com.example.demo.service.application;

import com.example.demo.dto.CreateAuthorDto;
import com.example.demo.dto.DisplayAuthorDto;


import java.util.List;
import java.util.Optional;

public interface AuthorApplicationService {
    Optional<DisplayAuthorDto> update(Long id, CreateAuthorDto authorDto) ;

    Optional<DisplayAuthorDto> save(CreateAuthorDto authorDto);

    Optional<DisplayAuthorDto> findById(Long id);

    List<DisplayAuthorDto> findAll();

    void deleteById(Long id);
}
