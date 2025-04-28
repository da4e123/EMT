package org.example.lab1.web;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.example.lab1.dto.WishlistDto;
import org.example.lab1.model.domain.User;
import org.example.lab1.service.application.WishlistApplicationService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/wishlist")
@Tag(name = "Wishlist API ", description = "Endpoint for managing wishlists")
public class WishlistController {

    private final WishlistApplicationService wishlistApplicationService;


    public WishlistController(WishlistApplicationService wishlistApplicationService) {
        this.wishlistApplicationService = wishlistApplicationService;
    }

    @Operation(summary = "Get the wishlist", description = "Retrieves a list of wishlist with all books")
    @GetMapping("")
    public ResponseEntity<List<WishlistDto>> getWishlist() {
        List<WishlistDto> wishlist = wishlistApplicationService.listAll();
        return ResponseEntity.ok(wishlist);
    }

//    @GetMapping("/{username}")
//    public ResponseEntity<WishlistDto> listAllBooksInWishlist(@PathVariable String username){
//        List<Book> books = wishlistApplicationService.listAllBooksInWishlist(username);
//
//        if (books.isEmpty()){
//            return ResponseEntity.noContent().build();
//        }
//
//        Wishlist wishlist = WishlistDto.from(books);
//
//
//    }

    @Operation(summary = "Add a book in wishlist", description = "Add a book in wishlist based on the give bookId.")
    @PostMapping("/add-book/{id}")
    public ResponseEntity<WishlistDto> addBookToWishlist(@PathVariable Long id, Authentication authentication){
        User user = (User) authentication.getPrincipal();
        return wishlistApplicationService.addBookInWishList(user.getUsername(), id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Rent all books in wishlist", description = "Allows user to rent all available books from their wishlist.")
    @PostMapping("/rent/{username}")
    public ResponseEntity<Void> rentFromWishlist(@PathVariable String username){
        wishlistApplicationService.rentAllFromWishlist(username);
        return ResponseEntity.ok().build();


    }



}
