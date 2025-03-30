package com.example.demo.service.application;

import com.example.demo.dto.CreateCountryDto;
import com.example.demo.dto.DisplayCountryDto;

import java.util.List;
import java.util.Optional;

public interface CountryApplicationService {
    Optional<DisplayCountryDto> update(Long id, CreateCountryDto countryDto) ;

    Optional<DisplayCountryDto> save(CreateCountryDto countryDto);

    Optional<DisplayCountryDto> findById(Long id);

    List<DisplayCountryDto> findAll();

    void deleteById(Long id);
}
