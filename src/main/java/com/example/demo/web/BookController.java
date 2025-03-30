package com.example.demo.web;


import com.example.demo.dto.CreateBookDto;
import com.example.demo.dto.DisplayBookDto;
import com.example.demo.model.domain.Book;
import com.example.demo.service.application.BookApplicationService;
import com.example.demo.service.domain.BookService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/books")
@Tag(name = "Book Controller", description = "Managing the books in the library")
public class BookController {
    private final BookApplicationService bookApplicationService;


    public BookController( BookApplicationService bookApplicationService) {
        this.bookApplicationService = bookApplicationService;
    }


    @GetMapping
    @Operation(summary = "Finding all the books", description = "Returns a list with all the available books in the library")
    public List<DisplayBookDto> findAll() {
        return bookApplicationService.findAll();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Finding a book by id", description = "Finds and returns a book by a given id")
    public ResponseEntity<DisplayBookDto> findById(@PathVariable Long id) {
        return bookApplicationService.findById(id)
                .map(c -> ResponseEntity.ok().body(c))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/add")
    @Operation(summary = "Adding a new book", description = "Adds a new book to the system")
    public ResponseEntity<DisplayBookDto> save(@RequestBody CreateBookDto createBookDto) {
        return bookApplicationService.save(createBookDto)
                .map(c -> ResponseEntity.ok().body(c))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @PutMapping("/edit/{id}")
    @Operation(summary = "Editing a book", description = "Edits and updates information for an existing book based on a given id")
    public ResponseEntity<DisplayBookDto> update(@PathVariable Long id, @RequestBody CreateBookDto createBookDto) {
        return bookApplicationService.update(id, createBookDto)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/delete/{id}")
    @Operation(summary = "Deleting a book", description = "Deletes a book from the system by a given id")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        if (bookApplicationService.findById(id).isPresent()) {
            bookApplicationService.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();

    }

    @PostMapping("/{id}/rent")
    @Operation(summary = "Renting a book", description = "Updates the number of available copies for a book when someone rents one")
    public Optional<DisplayBookDto> markAsRented(@PathVariable Long id) {
        return bookApplicationService.markAsRented(id);
    }

}
