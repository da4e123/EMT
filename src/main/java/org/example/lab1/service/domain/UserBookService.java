package org.example.lab1.service.domain;

import org.example.lab1.model.domain.Author;
import org.example.lab1.model.domain.Book;
import org.example.lab1.model.domain.UserBook;

import java.util.List;
import java.util.Optional;

public interface UserBookService {

     UserBook rentBook(Long book_id, String username);
     List<UserBook> getRentedBooks(String username);

     Optional<Book> mostRentedBook();

     String getMostActiveUser();

     Optional<Author> mostRentedByAuthor();






}
