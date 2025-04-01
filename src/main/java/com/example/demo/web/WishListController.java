package com.example.demo.web;


import com.example.demo.dto.AddToWishListDto;
import com.example.demo.dto.CreateBookDto;
import com.example.demo.dto.CreateWishListDto;
import com.example.demo.service.application.WishListApplicationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/wishlist")
@Tag(name = "WishList API", description = "Endpoints for managing user wishlists")
public class WishListController {

    private final WishListApplicationService wishListApplicationService;

    public WishListController(WishListApplicationService wishListApplicationService) {
        this.wishListApplicationService = wishListApplicationService;
    }

    @Operation(summary = "Get current user's wishlist",
            description = "Retrieves the current user's wishlist")
    @ApiResponse(responseCode = "200", description = "Successfully retrieved wishlist")
    @GetMapping
    public ResponseEntity<CreateWishListDto> getWishList() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        CreateWishListDto wishListDto = wishListApplicationService.getWishList(auth.getName());
        return ResponseEntity.ok(wishListDto);
    }

    @Operation(summary = "Get books in wishlist",
            description = "Retrieves all books in the current user's wishlist")
    @ApiResponse(responseCode = "200", description = "Successfully retrieved wishlist books")
    @GetMapping("/books")
    public ResponseEntity<List<CreateBookDto>> getWishListBooks() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        List<CreateBookDto> books = wishListApplicationService.getWishListBooks(auth.getName());
        return ResponseEntity.ok(books);
    }

    @Operation(summary = "Add book to wishlist",
            description = "Adds a book to the current user's wishlist")
    @ApiResponse(responseCode = "200", description = "Book successfully added to wishlist")
    @PostMapping("/add")
    public ResponseEntity<CreateWishListDto> addToWishList(@RequestBody AddToWishListDto addToWishListDto) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        CreateWishListDto wishListDto = wishListApplicationService.addToWishList(auth.getName(), addToWishListDto);
        return ResponseEntity.ok(wishListDto);
    }

    @Operation(summary = "Remove book from wishlist",
            description = "Removes a book from the current user's wishlist")
    @ApiResponse(responseCode = "200", description = "Book successfully removed from wishlist")
    @DeleteMapping("/remove/{bookId}")
    public ResponseEntity<CreateWishListDto> removeFromWishList(@PathVariable Long bookId) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        CreateWishListDto wishListDto = wishListApplicationService.removeFromWishList(auth.getName(), bookId);
        return ResponseEntity.ok(wishListDto);
    }

    @Operation(summary = "Clear wishlist",
            description = "Removes all books from the current user's wishlist")
    @ApiResponse(responseCode = "200", description = "Wishlist successfully cleared")
    @DeleteMapping("/clear")
    public ResponseEntity<CreateWishListDto> clearWishList() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        CreateWishListDto wishListDto = wishListApplicationService.clearWishList(auth.getName());
        return ResponseEntity.ok(wishListDto);
    }


    @Operation(summary = "Rent all books",
            description = "Rents all books from the current user's wishlist")
    @ApiResponse(responseCode = "200", description = "Books successfully rented")
    @PostMapping("/rent")
    public ResponseEntity<Void> rentAllBooks() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        wishListApplicationService.rentAllBooks(auth.getName());
        return ResponseEntity.ok().build();
    }
}
