package org.example.lab1.service.domain.impl;

import org.example.lab1.model.domain.Author;
import org.example.lab1.model.domain.Book;
import org.example.lab1.model.enums.Category;
import org.example.lab1.model.exceptions.InvalidAuthorId;
import org.example.lab1.model.exceptions.InvalidBookId;
import org.example.lab1.repository.BookRepository;
import org.example.lab1.service.domain.BookService;
import org.example.lab1.service.domain.AuthorService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {


    private final BookRepository bookRepository;
    private final AuthorService authorService;

    public BookServiceImpl(BookRepository bookRepository, AuthorService authorService) {
        this.bookRepository = bookRepository;
        this.authorService = authorService;
    }

    @Override
    public List<Book> listAll() {
        return bookRepository.findAll();
    }

    @Override
    public Optional<Book> findById(Long id) {
        return Optional.of(bookRepository.findById(id).orElseThrow(InvalidBookId::new));
    }

    @Override
    public Optional<Book> create(Book book) {
        return Optional.of(bookRepository.save(book));
    }

    @Override
    public Optional<Book> update(Long id, Book book) {
        return bookRepository.findById(id).map(existingBook -> {
            if(book.getName() != null){
                existingBook.setName(book.getName());
            }
            if (book.getAuthor() != null){
                existingBook.setAuthor(book.getAuthor());
            }

            if (book.getCategory() != null){
                existingBook.setCategory(book.getCategory());
            }
            if (book.getAvailableCopies() != null){
                existingBook.setAvailableCopies(book.getAvailableCopies());
            }

            return bookRepository.save(existingBook);


        });
    }

    @Override
    public void deleteById(Long id) {
        bookRepository.deleteById(id);
    }


    @Override
    public List<Book> filterByCategory(String category) {
        return listAll().stream().filter(c -> c.getCategory().name().equals(category)).toList();
    }



    @Override
    public void getCopy(Long id) {
        Book book = bookRepository.findById(id).orElseThrow(InvalidBookId::new);
        book.getCopy();
        this.bookRepository.save(book);
    }

    @Override
    public void returnCopy(Long id) {
        Book book = bookRepository.findById(id).orElseThrow(InvalidBookId::new);
        book.returnCopy();
        this.bookRepository.save(book);

    }

    //Lab 3 - additional task from school
    @Override
    public Book searchByName(String title) {
        return bookRepository.searchBookByName(title);
    }

}