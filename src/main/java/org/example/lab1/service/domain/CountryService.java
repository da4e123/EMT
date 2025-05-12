package org.example.lab1.service.domain;

import org.example.lab1.model.domain.Country;
import org.example.lab1.model.dto.AuthorDto;
import org.example.lab1.model.dto.CountryDto;
import org.example.lab1.model.exceptions.InvalidCountryId;

import java.util.List;
import java.util.Optional;

public interface CountryService {
    List<Country> listAllCountries();
    Optional<Country> findCountryById(Long countryId);

    Optional<Country> create(Country country);
    Optional<Country> update(Long id, Country country);

    Optional<Country> deleteCountry(Long id);

    //Lab 3 - additional task from school
    List<Country> findByContinent(String continent);
}
