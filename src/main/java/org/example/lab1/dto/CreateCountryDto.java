package org.example.lab1.dto;

import org.example.lab1.model.domain.Country;

public record CreateCountryDto(String name, String continent) {
    public Country toCountry(){
        return new Country(name,continent);
    }
}
