package com.example.demo.config;


import com.example.demo.model.Author;
import com.example.demo.model.Book;
import com.example.demo.model.Category;
import com.example.demo.model.Country;
import com.example.demo.repository.AuthorRepository;
import com.example.demo.repository.BookRepository;
import com.example.demo.repository.CountryRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final CountryRepository countryRepository;

    public DataInitializer(BookRepository bookRepository,
                           AuthorRepository authorRepository, CountryRepository countryRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
        this.countryRepository = countryRepository;
    }

    @PostConstruct
    public void init() {
        // Countries
        countryRepository.save(new Country("USA", "North America"));
        countryRepository.save(new Country("UK", "Europe"));
        countryRepository.save(new Country("Germany", "Europe"));
        countryRepository.save(new Country("India", "Asia"));
        countryRepository.save(new Country("Australia", "Oceania"));
        countryRepository.save(new Country("Canada", "North America"));

        // Authors
        authorRepository.save(new Author("George", "Orwell", countryRepository.findById(1L).orElseThrow()));
        authorRepository.save(new Author("J.K.", "Rowling", countryRepository.findById(2L).orElseThrow()));
        authorRepository.save(new Author("Leo", "Tolstoy", countryRepository.findById(3L).orElseThrow()));
        authorRepository.save(new Author("Mark", "Twain", countryRepository.findById(3L).orElseThrow()));

        // Books
        bookRepository.save(new Book("1984", Category.NOVEL,
                authorRepository.findById(1L).orElseThrow(), 5));
        bookRepository.save(new Book("Harry Potter and the Philosopher's Stone", Category.FANTASY,
                authorRepository.findById(2L).orElseThrow(), 3));
        bookRepository.save(new Book("War and Peace", Category.HISTORY,
                authorRepository.findById(3L).orElseThrow(), 10));
        bookRepository.save(new Book("Adventures of Huckleberry Finn", Category.DRAMA,
                authorRepository.findById(4L).orElseThrow(), 7));
    }
}
