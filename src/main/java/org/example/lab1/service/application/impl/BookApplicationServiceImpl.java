package org.example.lab1.service.application.impl;

import org.example.lab1.dto.CreateBookDto;
import org.example.lab1.dto.DisplayBookDto;
import org.example.lab1.model.domain.Author;
import org.example.lab1.model.enums.Category;
import org.example.lab1.model.exceptions.InvalidAuthorId;
import org.example.lab1.service.application.BookApplicationService;
import org.example.lab1.service.domain.AuthorService;
import org.example.lab1.service.domain.BookService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BookApplicationServiceImpl implements BookApplicationService {

    public final BookService bookService;
    public final AuthorService authorService;

    public BookApplicationServiceImpl(BookService bookService, AuthorService authorService) {
        this.bookService = bookService;
        this.authorService = authorService;
    }


    @Override
    public List<DisplayBookDto> listAll() {
        return DisplayBookDto.from(bookService.listAll());
    }

    @Override
    public Optional<DisplayBookDto> findById(Long id) {
        return bookService.findById(id).map(DisplayBookDto::from);
    }

    @Override
    public Optional<DisplayBookDto> create(CreateBookDto createBookDto) {
        Author author = authorService.findById(createBookDto.authorId()).orElseThrow(InvalidAuthorId::new);
        Category category = Category.valueOf(createBookDto.categoryName().toUpperCase());
        return bookService.create(createBookDto.toBook(category,author)).map(DisplayBookDto::from);
    }

    @Override
    public Optional<DisplayBookDto> update(Long id, CreateBookDto createBookDto) {
        Author author = authorService.findById(createBookDto.authorId()).orElseThrow(InvalidAuthorId::new);
        Category category = Category.valueOf(createBookDto.categoryName().toUpperCase());
        return bookService.update(id,createBookDto.toBook(category,author)).map(DisplayBookDto::from);
    }

    @Override
    public void deleteById(Long id) {
        bookService.deleteById(id);

    }

    @Override
    public List<DisplayBookDto> filterByCategory(String category) {
        return bookService.filterByCategory(category).stream().map(DisplayBookDto::from).collect(Collectors.toList());
    }


    @Override
    public void getCopy(Long id) {
        bookService.getCopy(id);

    }

    @Override
    public void returnCopy(Long id) {
        bookService.returnCopy(id);
    }
}
