package com.example.demo.service.application.impl;

import com.example.demo.dto.CreateBookDto;
import com.example.demo.dto.DisplayBookDto;
import com.example.demo.model.domain.Author;
import com.example.demo.model.domain.Book;
import com.example.demo.service.application.BookApplicationService;
import com.example.demo.service.domain.AuthorService;
import com.example.demo.service.domain.BookService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookApplicationServiceImpl implements BookApplicationService {

    private final BookService bookService;
    private final AuthorService authorService;


    public BookApplicationServiceImpl(BookService bookService, AuthorService authorService) {
        this.bookService = bookService;
        this.authorService = authorService;
    }


    @Override
    public Optional<DisplayBookDto> update(Long id, CreateBookDto bookDto) {
        Optional<Author> author = authorService.findById(bookDto.authorId());

        return bookService.update(id,bookDto.toBook(author.orElse(null))).map(DisplayBookDto::from);
    }

    @Override
    public Optional<DisplayBookDto> save(CreateBookDto bookDto) {
        Optional<Author> author = authorService.findById(bookDto.authorId());
        if(author.isPresent())
        {
            return bookService.save(bookDto.toBook(author.get())).map(DisplayBookDto::from);
        }
        return Optional.empty();
    }

    @Override
    public Optional<DisplayBookDto> findById(Long id) {
        return bookService.findById(id).map(DisplayBookDto::from);
    }

    @Override
    public List<DisplayBookDto> findAll() {
        return bookService.findAll().stream().map(DisplayBookDto::from).toList();
    }

    @Override
    public void deleteById(Long id) {
        bookService.deleteById(id);
    }

    @Override
    public Optional<DisplayBookDto> markAsRented(Long id) {
        return bookService.findById(id)
                .filter(book -> book.getAvailableCopies() > 0)
                .map(book -> {
                    book.setAvailableCopies(book.getAvailableCopies() - 1);
                    return book;
                })
                .map(bookService::save) // Now it returns Optional<Book>
                .flatMap(book -> book.map(DisplayBookDto::from));
    }
}
