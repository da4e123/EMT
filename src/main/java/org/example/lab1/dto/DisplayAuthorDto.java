package org.example.lab1.dto;

import org.example.lab1.model.domain.Author;

import java.util.List;
import java.util.stream.Collectors;

public record DisplayAuthorDto(Long id, String name, String surname, Long countryId) {
    public static DisplayAuthorDto from(Author author){
        return new DisplayAuthorDto(author.getId(), author.getName(), author.getSurname(), author.getCountry().getId());
    }

    public static List<DisplayAuthorDto> from(List<Author> authors){
        return authors.stream().map(DisplayAuthorDto::from).collect(Collectors.toList());
    }
}
