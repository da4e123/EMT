package org.example.lab1.service.impl;

import org.example.lab1.model.Author;
import org.example.lab1.model.Book;
import org.example.lab1.model.dto.BookDto;
import org.example.lab1.model.enums.Category;
import org.example.lab1.model.exceptions.InvalidAuthorId;
import org.example.lab1.model.exceptions.InvalidBookId;
import org.example.lab1.repository.BookRepository;
import org.example.lab1.service.AuthorService;
import org.example.lab1.service.BookService;
import org.springframework.stereotype.Service;

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
    public Optional<Book> create(String name, Category category, Long authorId, Integer avalailableCopies) {
        Author author = authorService.findById(authorId).orElseThrow(InvalidAuthorId::new);
        Book book = new Book(name,category,author,avalailableCopies);
        return Optional.of(bookRepository.save(book));
    }

    @Override
    public Optional<Book> update(Long id, String name, Category category, Long authorId, Integer availableCopies) {
        Book book = findById(id).orElseThrow(InvalidBookId::new);
        Author author = authorService.findById(authorId).orElseThrow(InvalidAuthorId::new);
        book.setName(name);
        book.setAuthor(author);
        book.setCategory(category);
        book.setAvailableCopies(availableCopies);
        return Optional.of(bookRepository.save(book));
    }

    @Override
    public Optional<Book> delete(Long id) {
        Book book = findById(id).orElseThrow(InvalidBookId::new);
        bookRepository.deleteById(id);
        return Optional.of(book);


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
}