package org.example.lab1.service.application.impl;


import org.example.lab1.dto.CreateAuthorDto;
import org.example.lab1.dto.DisplayAuthorDto;
import org.example.lab1.model.domain.Author;
import org.example.lab1.model.domain.Country;
import org.example.lab1.model.dto.AuthorDto;
import org.example.lab1.model.exceptions.InvalidAuthorId;
import org.example.lab1.model.projections.AuthorProjection;
import org.example.lab1.model.views.BooksPerAuthorView;
import org.example.lab1.repository.BooksPerAuthorViewRepository;
import org.example.lab1.service.application.AuthorApplicationService;
import org.example.lab1.service.domain.AuthorService;
import org.example.lab1.service.domain.CountryService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorApplicationServiceImpl implements AuthorApplicationService {

    public final AuthorService authorService;
    public final BooksPerAuthorViewRepository booksPerAuthorViewRepository;
    private final CountryService countryService;


    public AuthorApplicationServiceImpl(AuthorService authorService, BooksPerAuthorViewRepository booksPerAuthorViewRepository, CountryService countryService) {
        this.authorService = authorService;
        this.booksPerAuthorViewRepository = booksPerAuthorViewRepository;
        this.countryService = countryService;
    }


    @Override
    public List<DisplayAuthorDto> listAllAuthors() {
        return DisplayAuthorDto.from(authorService.listAllAuthors());
    }

    @Override
    public Optional<DisplayAuthorDto> findById(Long id) {
        return authorService.findById(id).map(DisplayAuthorDto::from);
    }

    @Override
    public Optional<DisplayAuthorDto> create(CreateAuthorDto createAuthor) {
        return authorService.create(createAuthor.toAuthor()).map(DisplayAuthorDto::from);
    }

    @Override
    public Optional<DisplayAuthorDto> update(Long authorId, CreateAuthorDto createAuthor) {
        return authorService.update(authorId, createAuthor.toAuthor()).map(DisplayAuthorDto::from);
    }

    @Override
    public void deleteById(Long id) {
        authorService.delete(id);
    }

    @Override
    public List<BooksPerAuthorView> findAllBooksPerAuthor() {
        return booksPerAuthorViewRepository.findAll();
    }

    @Override
    public BooksPerAuthorView findBookPerAuthor(Long id) {
        return booksPerAuthorViewRepository.findById(id).orElseThrow();
    }

    @Override
    public void refreshMaterializedView() {
        booksPerAuthorViewRepository.refreshMaterializedView();
    }

    @Override
    public List<AuthorProjection> findAllAuthorsNames() {
        return authorService.findAllAuthorsNames();
    }


    //Lab 3 - additional task from school
    @Override
    public List<AuthorDto> findByCountry(Long countryId) {
        return authorService.findByCountry(countryId);
    }


}
