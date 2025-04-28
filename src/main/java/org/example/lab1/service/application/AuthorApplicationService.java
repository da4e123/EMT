package org.example.lab1.service.application;

import org.example.lab1.dto.CreateAuthorDto;
import org.example.lab1.dto.DisplayAuthorDto;
import org.example.lab1.model.domain.Author;

import java.util.List;
import java.util.Optional;

public interface AuthorApplicationService {
    List<DisplayAuthorDto> listAllAuthors();
    Optional<DisplayAuthorDto> findById(Long id);
    Optional<DisplayAuthorDto> create(CreateAuthorDto createAuthor);

    Optional<DisplayAuthorDto> update(Long authorId, CreateAuthorDto createAuthor);
    void deleteById(Long id);
}
