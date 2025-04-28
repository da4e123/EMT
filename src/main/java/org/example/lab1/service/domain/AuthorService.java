package org.example.lab1.service.domain;

import org.example.lab1.model.domain.Author;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;



public interface AuthorService {

    List<Author> listAllAuthors();
    Optional<Author> findById(Long id);
    Optional<Author> create(Author author);

    Optional<Author> update(Long authorId, Author author);
    Optional<Author> delete(Long id);
}
