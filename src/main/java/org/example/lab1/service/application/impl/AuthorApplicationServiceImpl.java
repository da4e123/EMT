package org.example.lab1.service.application.impl;


import org.example.lab1.dto.CreateAuthorDto;
import org.example.lab1.dto.DisplayAuthorDto;
import org.example.lab1.model.exceptions.InvalidAuthorId;
import org.example.lab1.service.application.AuthorApplicationService;
import org.example.lab1.service.domain.AuthorService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorApplicationServiceImpl implements AuthorApplicationService {

    public final AuthorService authorService;


    public AuthorApplicationServiceImpl(AuthorService authorService) {
        this.authorService = authorService;
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


}
