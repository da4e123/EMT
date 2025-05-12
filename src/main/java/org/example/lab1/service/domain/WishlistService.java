package org.example.lab1.service.domain;


import org.example.lab1.model.domain.Book;
import org.example.lab1.model.domain.Wishlist;

import java.util.List;
import java.util.Optional;


public interface WishlistService {

    List<Wishlist> listAll(String token);

    Optional<Wishlist> getWishlistByUsername(String username,String token);


    List<Book> listAllBooksInWishlist(String username,String token);

    Optional<Wishlist> addBookInWishList(String username, Long bookId, String token);



    void rentAllFromWishlist(String username, String token);


}
