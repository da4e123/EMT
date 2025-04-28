package org.example.lab1.dto;

import org.example.lab1.model.domain.Author;
import org.example.lab1.model.domain.Country;

public record CreateAuthorDto(String name, String surname, Country country) {

    public Author toAuthor(){
        return new Author(name,surname,country);
    }
}
