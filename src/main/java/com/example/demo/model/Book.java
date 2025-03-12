package com.example.demo.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Book {

    @Id @GeneratedValue
    Long id;
    String name;
    @Enumerated(value = EnumType.STRING)
    Category Category;
    @ManyToOne
    Author author;
    Integer availableCopies;

    public Book(Long id, String name, Category category, Author author, Integer availableCopies) {
        this.id = id;
        this.name = name;
        Category = category;
        this.author = author;
        this.availableCopies = availableCopies;
    }

    public Book(String name, com.example.demo.model.Category category, Author author, Integer availableCopies) {
        this.name = name;
        Category = category;
        this.author = author;
        this.availableCopies = availableCopies;
    }

    public Book() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Category getCategory() {
        return Category;
    }

    public void setCategory(Category category) {
        Category = category;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public Integer getAvailableCopies() {
        return availableCopies;
    }

    public void setAvailableCopies(Integer availableCopies) {
        this.availableCopies = availableCopies;
    }
}
