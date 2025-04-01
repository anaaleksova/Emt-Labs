package com.example.demo.service.application;


import com.example.demo.dto.AddToWishListDto;
import com.example.demo.dto.CreateBookDto;
import com.example.demo.dto.CreateWishListDto;

import java.util.List;

public interface WishListApplicationService {

    CreateWishListDto getWishList(String username);
    CreateWishListDto addToWishList(String username, AddToWishListDto addToWishListDto);
    CreateWishListDto removeFromWishList(String username, Long bookId);
    List<CreateBookDto> getWishListBooks(String username);
    CreateWishListDto clearWishList(String username);
    void rentAllBooks(String username);
}
