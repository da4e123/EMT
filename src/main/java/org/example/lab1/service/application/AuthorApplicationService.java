package org.example.lab1.service.application;

import org.example.lab1.dto.CreateAuthorDto;
import org.example.lab1.dto.DisplayAuthorDto;
import org.example.lab1.model.domain.Author;
import org.example.lab1.model.domain.Book;
import org.example.lab1.model.dto.AuthorDto;
import org.example.lab1.model.projections.AuthorProjection;
import org.example.lab1.model.views.BooksPerAuthorView;

import java.util.List;
import java.util.Optional;

public interface AuthorApplicationService {
    List<DisplayAuthorDto> listAllAuthors();
    Optional<DisplayAuthorDto> findById(Long id);
    Optional<DisplayAuthorDto> create(CreateAuthorDto createAuthor);

    Optional<DisplayAuthorDto> update(Long authorId, CreateAuthorDto createAuthor);
    void deleteById(Long id);

    List<BooksPerAuthorView> findAllBooksPerAuthor();
    BooksPerAuthorView findBookPerAuthor(Long id);

    void refreshMaterializedView();

    List<AuthorProjection> findAllAuthorsNames();

    //Lab 3 - additional task from school
    List<AuthorDto> findByCountry(Long countryId);
}
