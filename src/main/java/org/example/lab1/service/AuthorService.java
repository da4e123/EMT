package org.example.lab1.service;

import org.example.lab1.model.Author;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;



public interface AuthorService {

    List<Author> listAllAuthors();
    Optional<Author> findById(Long id);
    Optional<Author> create(String name, String surname, Long countryId);

    Optional<Author> update(Long authorId, String name, String surname, Long countryId);
    Optional<Author> delete(Long id);
}
