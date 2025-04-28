package org.example.lab1.service.application;

import org.example.lab1.dto.CreateCountryDto;
import org.example.lab1.dto.DisplayCountryDto;
import org.example.lab1.model.domain.Country;

import java.util.List;
import java.util.Optional;

public interface CountryApplicationService {

    List<DisplayCountryDto> listAllCountries();
    Optional<DisplayCountryDto> findCountryById(Long countryId);

    Optional<DisplayCountryDto> create(CreateCountryDto createCountryDto);
    Optional<DisplayCountryDto> update(Long id, CreateCountryDto createCountryDto);

    Optional<DisplayCountryDto> deleteCountry(Long id);
}
