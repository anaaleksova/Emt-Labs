package com.example.demo.service.application.impl;

import com.example.demo.dto.CreateCountryDto;
import com.example.demo.dto.DisplayBookDto;
import com.example.demo.dto.DisplayCountryDto;
import com.example.demo.service.application.CountryApplicationService;
import com.example.demo.service.domain.CountryService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CountryApplicationServiceImpl implements CountryApplicationService {

    private final CountryService countryService;

    public CountryApplicationServiceImpl(CountryService countryService) {
        this.countryService = countryService;
    }

    @Override
    public Optional<DisplayCountryDto> update(Long id, CreateCountryDto countryDto) {
        return countryService.update(id,countryDto.toCountry()).map(DisplayCountryDto::from);
    }

    @Override
    public Optional<DisplayCountryDto> save(CreateCountryDto countryDto) {
        return countryService.save(countryDto.toCountry())
                .map(DisplayCountryDto::from);

    }

    @Override
    public Optional<DisplayCountryDto> findById(Long id) {
        return countryService.findById(id).map(DisplayCountryDto::from);
    }

    @Override
    public List<DisplayCountryDto> findAll() {
        return countryService.findAll().stream().map(DisplayCountryDto::from).toList();
    }

    @Override
    public void deleteById(Long id) {
        countryService.deleteById(id);
    }
}
