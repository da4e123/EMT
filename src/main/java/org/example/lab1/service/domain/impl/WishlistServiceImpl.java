package org.example.lab1.service.domain.impl;


import org.example.lab1.model.domain.Book;
import org.example.lab1.model.domain.User;
import org.example.lab1.model.domain.Wishlist;
import org.example.lab1.model.exceptions.InvalidBookId;
import org.example.lab1.model.exceptions.NotAvailableCopiesOfThisBook;
import org.example.lab1.repository.BookRepository;
import org.example.lab1.repository.WishlistRepository;
import org.example.lab1.service.domain.BookService;
import org.example.lab1.service.domain.UserService;
import org.example.lab1.service.domain.WishlistService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class WishlistServiceImpl implements WishlistService {

    private final WishlistRepository wishlistRepository;
    private final UserService userService;

    private final BookService bookService;

    private final BookRepository bookRepository;

    public WishlistServiceImpl(WishlistRepository wishlistRepository, UserService userService, BookService bookService, BookRepository bookRepository) {
        this.wishlistRepository = wishlistRepository;
        this.userService = userService;
        this.bookService = bookService;
        this.bookRepository = bookRepository;
    }

    @Override
    public List<Wishlist> listAll(String token) {
        return userService.getAuthenticatedUser(token).getWishlist();
    }

    @Override
    public Optional<Wishlist> getWishlistByUsername(String username, String token) {
        User authUser = userService.getAuthenticatedUser(token);


        return Optional.of(wishlistRepository.findByUser(authUser).orElseGet(() -> wishlistRepository.save(new Wishlist(authUser))));
    }

    @Override
    public List<Book> listAllBooksInWishlist(String username, String token) {
        User authUser = userService.getAuthenticatedUser(token);

        Optional<Wishlist> wishlist = wishlistRepository.findByUser(authUser);

        return wishlist.get().getBooks();
    }

//    @Override
//    public Optional<Wishlist> listAllBooksInWishlist(String  username) {
//        User user = userService.findByUsername(username);
//            if (wishlistRepository.findByUser(user).isEmpty()){
//                throw new RuntimeException("The Wishlist is empty");
//            }
//
//        return wishlistRepository.findByUser(user);
//    }

    @Override
    public Optional<Wishlist> addBookInWishList(String username, Long bookId, String token) {


        if (getWishlistByUsername(username, token).isPresent()){
            User authUser = userService.getAuthenticatedUser(token);
            Book book = bookService.findById(bookId).orElseThrow(InvalidBookId::new);
            if (book.getAvailableCopies() <= 0){
                throw new NotAvailableCopiesOfThisBook();
            }
            Wishlist wishlist = wishlistRepository.findByUser(authUser)
                            .orElse(new Wishlist(authUser));


            wishlist.getBooks().add(book);

            return Optional.of(wishlistRepository.save(wishlist));
        }

        return Optional.empty();


    }

    @Override
    public void rentAllFromWishlist(String username, String token) {

        User authUser = userService.getAuthenticatedUser(token);
        Wishlist wishlist = wishlistRepository.findByUser(authUser).orElseThrow(() -> new RuntimeException("Wishlist not found for user!"));

        if (wishlist.getBooks().isEmpty()){
            throw new RuntimeException("Wishlist is empty!");

        }

        List<Book> rentedBooks = new ArrayList<>();


        for (Book book : wishlist.getBooks()){
            if (book.getAvailableCopies() > 0){
                book.setAvailableCopies(book.getAvailableCopies() - 1);
                rentedBooks.add(book);
            } else {
                throw new RuntimeException("Book " + book.getName() + " is not available for rent!");

            }


        }

        bookRepository.saveAll(rentedBooks);
        wishlist.getBooks().clear();
        wishlistRepository.save(wishlist);



    }
}
