package org.example.lab1.service;

import org.example.lab1.model.Country;
import org.example.lab1.model.dto.CountryDto;
import org.example.lab1.model.exceptions.InvalidCountryId;

import java.util.List;
import java.util.Optional;

public interface CountryService {
    List<Country> listAllCountries();
    Optional<Country> findCountryById(Long countryId);

    Optional<Country> create(String name, String continent);
    Optional<Country> update(Long id, String name, String continent);

    Optional<Country> deleteCountry(Long id);
}
