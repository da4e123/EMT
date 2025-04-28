package org.example.lab1.service.application;

import org.example.lab1.dto.DisplayBookDto;
import org.example.lab1.dto.WishlistDto;
import org.example.lab1.model.domain.Book;

import java.util.List;
import java.util.Optional;

public interface WishlistApplicationService {

    List<WishlistDto> listAll();
    List<Book> listAllBooksInWishlist(String username);

    Optional<WishlistDto> addBookInWishList(String username, Long bookId);

    void rentAllFromWishlist(String username);
}
