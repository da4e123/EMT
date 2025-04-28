package org.example.lab1.service.domain;


import org.example.lab1.model.domain.Book;
import org.example.lab1.model.domain.Wishlist;

import java.util.List;
import java.util.Optional;


public interface WishlistService {

    List<Wishlist> listAll();

    Optional<Wishlist> getWishlistByUsername(String username);


    List<Book> listAllBooksInWishlist(String username);

    Optional<Wishlist> addBookInWishList(String username, Long bookId);



    void rentAllFromWishlist(String username);


}
