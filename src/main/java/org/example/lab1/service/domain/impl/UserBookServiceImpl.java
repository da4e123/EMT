package org.example.lab1.service.domain.impl;

import org.example.lab1.model.domain.Author;
import org.example.lab1.model.domain.Book;
import org.example.lab1.model.domain.UserBook;
import org.example.lab1.repository.AuthorRepository;
import org.example.lab1.repository.BookRepository;
import org.example.lab1.repository.UserBookRepository;
import org.example.lab1.service.domain.UserBookService;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserBookServiceImpl implements UserBookService {

    private final UserBookRepository userBookRepository;
    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;

    public UserBookServiceImpl(UserBookRepository userBookRepository, BookRepository bookRepository, AuthorRepository authorRepository) {
        this.userBookRepository = userBookRepository;
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
    }

    @Override
    public UserBook rentBook(Long book_id, String username) {
        UserBook userBook = new UserBook();
        userBook.setBook_id(book_id);
        userBook.setUsername(username);

        return userBookRepository.save(userBook);
    }

    @Override
    public List<UserBook> getRentedBooks(String username) {
        return userBookRepository.findByUsername(username);
    }

    @Override
    public Optional<Book> mostRentedBook() {
        List<UserBook> rentalsBooks = userBookRepository.findAll();

        Map<Long, Integer> map = new HashMap<>();

        for(UserBook userBook : rentalsBooks){
            if (map.containsKey(userBook.getBook_id())){
                map.put(userBook.getBook_id(), map.get(userBook.getBook_id()) + 1);
            }
            else {
                map.put(userBook.getBook_id(), 1);
            }
        }
        var max = 0;
        Long maxId = 0L;
        for (Map.Entry<Long, Integer> entry : map.entrySet()){
            if (entry.getValue() > max){
                max = entry.getValue();
                maxId = entry.getKey();
            }
        }

        return bookRepository.findById(maxId);
    }

    @Override
    public String getMostActiveUser() {
        List<UserBook> rentalBooks = userBookRepository.findAll();

        return rentalBooks.stream().collect(Collectors.groupingBy(UserBook::getUsername, Collectors.counting()))
                .entrySet().stream().max(Comparator.comparing(Map.Entry::getValue))
                .map(Map.Entry::getKey)
                .orElse(null);
    }

    @Override
    public Optional<Author> mostRentedByAuthor() {

        List<UserBook> rentalsBooks = userBookRepository.findAll();

        Map<Long, Integer> authorMap = new HashMap<>();

        for (UserBook userBook : rentalsBooks) {
            bookRepository.findById(userBook.getBook_id()).ifPresent(book -> {
                Long authorId = book.getAuthor().getId();
                authorMap.put(authorId, authorMap.getOrDefault(authorId, 0) + 1);
            });
        }

        var max = 0;
        Long maxId = 0L;
        for (Map.Entry<Long, Integer> entry : authorMap.entrySet()) {
            if (entry.getValue() > max){
                max = entry.getValue();
                maxId = entry.getKey();
            }
        }

        return authorRepository.findById(maxId);

    }


}
