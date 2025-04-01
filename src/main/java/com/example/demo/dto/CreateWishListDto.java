package com.example.demo.dto;

import com.example.demo.model.domain.Author;
import com.example.demo.model.domain.Book;
import com.example.demo.model.domain.WishList;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public record CreateWishListDto ( String username,
                                  List<Long> bookIds){


    public static CreateWishListDto from(WishList wishList) {
        String username = wishList.getUser() != null ? wishList.getUser().getUsername() : null;

        List<Long> bookIds = Collections.emptyList();
        if (wishList.getBooks() != null) {
            bookIds = wishList.getBooks().stream()
                    .map(Book::getId)
                    .collect(Collectors.toList());
        }

        return new CreateWishListDto(username, bookIds);
    }

}
