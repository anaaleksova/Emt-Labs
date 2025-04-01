package com.example.demo.service.domain;


import com.example.demo.dto.AddToWishListDto;
import com.example.demo.dto.CreateBookDto;
import com.example.demo.dto.CreateWishListDto;
import com.example.demo.model.domain.Book;
import com.example.demo.model.domain.WishList;

import java.util.List;

public interface WishListService {

    WishList getOrCreateWishList(String username);
    WishList addBookToWishList(String username, Long bookId);
    WishList removeBookFromWishList(String username, Long bookId);
    List<Book> getWishListBooks(String username);
    WishList clearWishList(String username);
    void rentAllBooks(String username);

}
