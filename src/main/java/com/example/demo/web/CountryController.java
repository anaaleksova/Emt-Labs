package com.example.demo.web;

import com.example.demo.dto.CreateAuthorDto;
import com.example.demo.dto.CreateCountryDto;
import com.example.demo.dto.DisplayAuthorDto;
import com.example.demo.dto.DisplayCountryDto;
import com.example.demo.service.application.CountryApplicationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/countries")
@Tag(name = "Country Controller", description = "Managing the countries in the system")
public class CountryController {

    private final CountryApplicationService countryApplicationService;

    public CountryController(CountryApplicationService countryApplicationService) {
        this.countryApplicationService = countryApplicationService;
    }

    @GetMapping
    @Operation(summary = "Finding all the countries", description = "Returns a list with all the countries in the system")
    public List<DisplayCountryDto> findAll() {
        return countryApplicationService.findAll();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Finding a country by id", description = "Finds and returns a country by a given id")
    public ResponseEntity<DisplayCountryDto> findById(@PathVariable Long id) {
        return countryApplicationService.findById(id)
                .map(c -> ResponseEntity.ok().body(c))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/add")
    @Operation(summary = "Adding a new country", description = "Adds a new country to the system")
    public ResponseEntity<DisplayCountryDto> save(@RequestBody CreateCountryDto createCountryDto) {
        return countryApplicationService.save(createCountryDto)
                .map(c -> ResponseEntity.ok().body(c))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @PutMapping("/edit/{id}")
    @Operation(summary = "Editing a country", description = "Edits and updates information for an existing country based on a given id")
    public ResponseEntity<DisplayCountryDto> update(@PathVariable Long id, @RequestBody CreateCountryDto createCountryDto) {
        return countryApplicationService.update(id, createCountryDto)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/delete/{id}")
    @Operation(summary = "Deleting a country", description = "Deletes a country from the system by a given id")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        if (countryApplicationService.findById(id).isPresent()) {
            countryApplicationService.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();

    }

}
