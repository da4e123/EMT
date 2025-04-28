package org.example.lab1.dto;

import org.example.lab1.model.domain.Author;
import org.example.lab1.model.domain.Book;
import org.example.lab1.model.enums.Category;

import java.util.List;
import java.util.stream.Collectors;

public record CreateBookDto(String name, String categoryName, Long authorId, Integer availableCopies) {

    public static CreateBookDto from(Book book){
        return new CreateBookDto(book.getName(), book.getCategory().name(),book.getAuthor().getId(), book.getAvailableCopies());
    }

    public static List<CreateBookDto> from(List<Book> books){
        return books.stream().map(CreateBookDto::from).collect(Collectors.toList());
    }
    public Book toBook(Category category, Author author){
        return new Book(name,category,author,availableCopies);
    }
}
