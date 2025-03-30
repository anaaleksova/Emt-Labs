package com.example.demo.web;

import com.example.demo.dto.CreateAuthorDto;
import com.example.demo.dto.CreateBookDto;
import com.example.demo.dto.DisplayAuthorDto;
import com.example.demo.dto.DisplayBookDto;
import com.example.demo.service.application.AuthorApplicationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/authors")
@Tag(name = "Author Controller", description = "Managing the authors in the system")
public class AuthorController {
    private final AuthorApplicationService authorApplicationService;

    public AuthorController(AuthorApplicationService authorApplicationService) {
        this.authorApplicationService = authorApplicationService;
    }

    @GetMapping
    @Operation(summary = "Finding all the authors", description = "Returns a list with all the authors in the system")
    public List<DisplayAuthorDto> findAll() {
        return authorApplicationService.findAll();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Finding an author by id", description = "Finds and returns an author by a given id")
    public ResponseEntity<DisplayAuthorDto> findById(@PathVariable Long id) {
        return authorApplicationService.findById(id)
                .map(c -> ResponseEntity.ok().body(c))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/add")
    @Operation(summary = "Adding a new author", description = "Adds a new author to the system")
    public ResponseEntity<DisplayAuthorDto> save(@RequestBody CreateAuthorDto createAuthorDto) {
        return authorApplicationService.save(createAuthorDto)
                .map(c -> ResponseEntity.ok().body(c))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @PutMapping("/edit/{id}")
    @Operation(summary = "Editing an author", description = "Edits and updates information for an existing author based on a given id")
    public ResponseEntity<DisplayAuthorDto> update(@PathVariable Long id, @RequestBody CreateAuthorDto createAuthorDto) {
        return authorApplicationService.update(id, createAuthorDto)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/delete/{id}")
    @Operation(summary = "Deleting an author", description = "Deletes an author from the system by a given id")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        if (authorApplicationService.findById(id).isPresent()) {
            authorApplicationService.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();

    }

}
