package com.example.demo.dto;

import com.example.demo.model.domain.Country;

public record CreateCountryDto(String name, String continent){

    public Country toCountry() {
        return new Country(name, continent);
    }

}
