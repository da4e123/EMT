package org.example.lab1.model.dto;

import lombok.Data;

@Data
public class AuthorDto {
    public String name;
    public String surname;
    public Long country;

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public Long getCountry() {
        return country;
    }
}
