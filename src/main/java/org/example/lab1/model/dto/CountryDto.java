package org.example.lab1.model.dto;

import lombok.Data;

@Data
public class CountryDto {
    private String name;
    private String continent;

    public String getName() {
        return name;
    }

    public String getContinent() {
        return continent;
    }
}
