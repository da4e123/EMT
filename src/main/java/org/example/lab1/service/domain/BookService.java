package org.example.lab1.service.domain;

import org.example.lab1.model.domain.Book;
import org.example.lab1.model.enums.Category;

import java.util.List;
import java.util.Optional;

public interface BookService {
    List<Book> listAll();

    Optional<Book> findById(Long id);

    Optional<Book> create(Book book);
    Optional<Book> update(Long id, Book book);

//    Optional<Book> delete(Long id);

    void deleteById(Long id);

    List<Book> filterByCategory(String category);

    void getCopy(Long id);
    void returnCopy(Long id);



}
