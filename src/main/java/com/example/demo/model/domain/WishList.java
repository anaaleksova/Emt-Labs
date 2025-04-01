package com.example.demo.model.domain;

import com.example.demo.model.enumerations.Category;
import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class WishList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToMany
    List<Book> books;
    @OneToOne
    User user;

    public WishList(Long id, List<Book> books, User user) {
        this.id = id;
        this.books = books;
        this.user = user;
    }

    public WishList() {

    }

    public WishList(User user) {
        this.user = user;
        this.books = new ArrayList<>();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void addBook(Book book) {
        if (books == null) {
            books = new ArrayList<>();
        }
        if (!books.contains(book)) {
            books.add(book);
        }
    }

    public void removeBook(Book book) {
        if (books != null) {
            books.remove(book);
        }
    }
}
