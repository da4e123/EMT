package org.example.lab1.web;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.example.lab1.model.domain.Author;
import org.example.lab1.model.domain.Book;
import org.example.lab1.model.domain.UserBook;
import org.example.lab1.service.domain.UserBookService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user-book")
@Tag(name = "UserBook API ", description = "Endpoint for managing UserBook")
public class UserBookController {

    private final UserBookService userBookService;


    public UserBookController(UserBookService userBookService) {
        this.userBookService = userBookService;
    }

    @PostMapping("/rent")
    public ResponseEntity<UserBook> rentBook(@RequestParam Long bookId, @RequestParam String username){
        return ResponseEntity.ok(userBookService.rentBook(bookId,username));
    }

    @GetMapping("/{username}")
    public ResponseEntity<List<UserBook>> getRentedBooks(@PathVariable String username){
        return ResponseEntity.ok(userBookService.getRentedBooks(username));
    }

    @GetMapping("/most-rented-book")
    public ResponseEntity<Optional<Book>> mostRentedBooks(){
        return ResponseEntity.ok(userBookService.mostRentedBook());
    }

    @GetMapping("/most-active-user")
    public ResponseEntity<String> mostActiveUser(){
        return ResponseEntity.ok(userBookService.getMostActiveUser());
    }

    @GetMapping("/most-popular-author")
    public ResponseEntity<Optional<Author>> mostPopularAuthor(){
        return ResponseEntity.ok(userBookService.mostRentedByAuthor());
    }


}
