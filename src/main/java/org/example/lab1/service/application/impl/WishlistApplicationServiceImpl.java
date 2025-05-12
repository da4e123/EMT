package org.example.lab1.service.application.impl;


import org.example.lab1.dto.DisplayBookDto;
import org.example.lab1.dto.WishlistDto;
import org.example.lab1.model.domain.Book;
import org.example.lab1.model.domain.User;
import org.example.lab1.service.application.WishlistApplicationService;
import org.example.lab1.service.domain.UserService;
import org.example.lab1.service.domain.WishlistService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WishlistApplicationServiceImpl implements WishlistApplicationService {

    private final WishlistService wishlistService;
    private final UserService userService;

    public WishlistApplicationServiceImpl(WishlistService wishlistService, UserService userService) {
        this.wishlistService = wishlistService;
        this.userService = userService;
    }

    @Override
    public List<WishlistDto> listAll(String token) {
        return WishlistDto.from(wishlistService.listAll(token));
    }

    @Override
    public List<Book> listAllBooksInWishlist(String username, String token) {
        return wishlistService.listAllBooksInWishlist(username,token);
    }

//    @Override
//    public Optional<WishlistDto> listAllBooksInWishlist(String username) {
//        return wishlistService.listAllBooksInWishlist(username).map(WishlistDto::from);
//
//    }

    @Override
    public Optional<WishlistDto> addBookInWishList(String username, Long bookId, String token) {
        return wishlistService.addBookInWishList(username,bookId, token).map(WishlistDto::from);
    }

    @Override
    public void rentAllFromWishlist(String username, String token) {
        wishlistService.rentAllFromWishlist(username, token);
    }
}
