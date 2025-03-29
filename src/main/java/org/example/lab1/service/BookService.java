package org.example.lab1.service;

import org.example.lab1.model.Book;
import org.example.lab1.model.dto.BookDto;
import org.example.lab1.model.enums.Category;

import java.util.List;
import java.util.Optional;

public interface BookService {
    List<Book> listAll();

    Optional<Book> findById(Long id);

    Optional<Book> create(String name, Category category, Long authorId, Integer avalailableCopies);
    Optional<Book> update(Long id, String name, Category category, Long authorId, Integer availableCopies);

    Optional<Book> delete(Long id);

    List<Book> filterByCategory(String category);

    void getCopy(Long id);
    void returnCopy(Long id);



}
