package com.example.demo.service.domain.impl;


import com.example.demo.model.domain.Book;
import com.example.demo.model.domain.User;
import com.example.demo.model.domain.WishList;
import com.example.demo.model.exceptions.BookNotFoundException;
import com.example.demo.model.exceptions.NoAvailableCopiesException;
import com.example.demo.model.exceptions.UserNotFoundException;
import com.example.demo.repository.BookRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.repository.WishListRepository;
import com.example.demo.service.domain.BookService;
import com.example.demo.service.domain.WishListService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class WishListServiceImpl implements WishListService {

    private final WishListRepository wishListRepository;
    private final UserRepository userRepository;
    private final BookRepository bookRepository;
    private final BookService bookService;

    public WishListServiceImpl(WishListRepository wishListRepository,
                               UserRepository userRepository,
                               BookRepository bookRepository, BookService bookService) {
        this.wishListRepository = wishListRepository;
        this.userRepository = userRepository;
        this.bookRepository = bookRepository;
        this.bookService = bookService;
    }

    public WishList getOrCreateWishList(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException(username));

        return wishListRepository.findByUserUsername(username)
                .orElseGet(() -> {
                    WishList wishList = new WishList(user);
                    return wishListRepository.save(wishList);
                });
    }


    @Transactional
    public WishList addBookToWishList(String username, Long bookId) {
        WishList wishList = getOrCreateWishList(username);
        Book book = bookRepository.findById(bookId)
                .orElseThrow(BookNotFoundException::new);
        Integer available = book.getAvailableCopies();
        if(available<1)
        {
            throw new NoAvailableCopiesException("Book '" + book.getName() + "' is out of stock.");

        }
        wishList.addBook(book);
        return wishListRepository.save(wishList);
    }


    public WishList removeBookFromWishList(String username, Long bookId) {
        WishList wishList = getOrCreateWishList(username);
        Book book = bookRepository.findById(bookId)
                .orElseThrow(BookNotFoundException::new);

        wishList.removeBook(book);
        return wishListRepository.save(wishList);
    }

    public List<Book> getWishListBooks(String username) {
        WishList wishList = getOrCreateWishList(username);
        return wishList.getBooks();
    }

    public WishList clearWishList(String username) {
        WishList wishList = getOrCreateWishList(username);
        wishList.getBooks().clear();
        return wishListRepository.save(wishList);
    }

    @Override
    public void rentAllBooks(String username) {
        List<Book> books = this.getWishListBooks(username);
        books.forEach(book -> {
            if (book.getAvailableCopies() <= 0) {
                throw new NoAvailableCopiesException("Book '" + book.getName() + "' is out of stock.");
            }
            bookService.markAsRented(book.getId());
        });
    }
}