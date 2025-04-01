package com.example.demo.service.application.impl;

import com.example.demo.dto.AddToWishListDto;
import com.example.demo.dto.CreateBookDto;
import com.example.demo.dto.CreateWishListDto;
import com.example.demo.model.domain.Book;
import com.example.demo.model.domain.WishList;
import com.example.demo.service.application.WishListApplicationService;
import com.example.demo.service.domain.WishListService;
import com.example.demo.service.domain.impl.WishListServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class WishListApplicationServiceImpl implements WishListApplicationService {

    private final WishListServiceImpl wishListService;

    public WishListApplicationServiceImpl(WishListServiceImpl wishListService) {
        this.wishListService = wishListService;
    }

    @Override
    public CreateWishListDto getWishList(String username) {
        WishList wishList = wishListService.getOrCreateWishList(username);
        return CreateWishListDto.from(wishList);
    }

    @Override
    public CreateWishListDto addToWishList(String username, AddToWishListDto addToWishListDto) {
        WishList wishList = wishListService.addBookToWishList(username, addToWishListDto.bookId());
        CreateWishListDto result = CreateWishListDto.from(wishList);
        return result;
    }

    @Override
    public CreateWishListDto removeFromWishList(String username, Long bookId) {
        WishList wishList = wishListService.removeBookFromWishList(username, bookId);
        return CreateWishListDto.from(wishList);
    }

    @Override
    public List<CreateBookDto> getWishListBooks(String username) {
        List<Book> books = wishListService.getWishListBooks(username);
        return books.stream()
                .map(CreateBookDto::from)
                .collect(Collectors.toList());
    }

    @Override
    public CreateWishListDto clearWishList(String username) {
        WishList wishList = wishListService.clearWishList(username);
        return CreateWishListDto.from(wishList);
    }

    @Override
    public void rentAllBooks(String username) {
        wishListService.rentAllBooks(username);
    }
}
