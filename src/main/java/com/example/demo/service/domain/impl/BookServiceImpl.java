package com.example.demo.service.domain.impl;

import com.example.demo.repository.AuthorRepository;
import com.example.demo.service.domain.BookService;
import com.example.demo.model.domain.Book;
import org.springframework.stereotype.Service;
import com.example.demo.repository.BookRepository;

import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    public BookServiceImpl(BookRepository bookRepository, AuthorRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
    }

    @Override
    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    @Override
    public Optional<Book> save(Book book) {
        if (book.getAuthor() != null &&
                authorRepository.findById(book.getAuthor().getId()).isPresent()) {
            return Optional.of(
                    bookRepository.save(new Book(book.getName(),
                            book.getCategory(),
                            authorRepository.findById(book.getAuthor().getId()).get(),
                            book.getAvailableCopies())));
        }
        return Optional.empty();
    }


    @Override
    public Optional<Book> findById(Long id) {
        return bookRepository.findById(id);
    }

    @Override
    public Optional<Book> update(Long id, Book book) {
        return bookRepository.findById(id).map(existingBook -> {
            if (book.getName() != null) {
                existingBook.setName(book.getName());
            }
            if (book.getCategory() != null) {
                existingBook.setCategory(book.getCategory());
            }
            if (book.getAuthor() != null  && authorRepository.findById(book.getAuthor().getId()).isPresent()) {
                existingBook.setAuthor(authorRepository.findById(book.getAuthor().getId()).get());
            }
            if (book.getAvailableCopies() != null) {
                existingBook.setAvailableCopies(book.getAvailableCopies());
            }
            return bookRepository.save(existingBook);
        });

    }

    @Override
    public Optional<Book> markAsRented(Long id) {
        return bookRepository.findById(id)
                .filter(book -> book.getAvailableCopies() > 0)
                .map(book -> {
                    book.setAvailableCopies(book.getAvailableCopies() - 1);
                    return bookRepository.save(book);
                });
    }

    @Override
    public void deleteById(Long id) {
        bookRepository.deleteById(id);
    }


}
