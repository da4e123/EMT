package org.example.lab1.dto;

import org.example.lab1.model.domain.Author;
import org.example.lab1.model.domain.Book;
import org.example.lab1.model.enums.Category;

import java.util.List;
import java.util.stream.Collectors;

public record DisplayBookDto(Long id, String name, String categoryName, Long authorId, Integer availableCopies) {
    public static DisplayBookDto from(Book book){
        return new DisplayBookDto(book.getId(), book.getName(), book.getCategory().name(),book.getAuthor().getId(), book.getAvailableCopies());
    }

    public static List<DisplayBookDto> from(List<Book> books){
        return books.stream().map(DisplayBookDto::from).collect(Collectors.toList());
    }

    public Book toBook(Category category, Author author){
        return new Book(name,category,author,availableCopies);
    }
}
