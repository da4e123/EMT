package org.example.lab1.service.application;

import org.example.lab1.dto.CreateCountryDto;
import org.example.lab1.dto.DisplayCountryDto;
import org.example.lab1.model.domain.Country;
import org.example.lab1.model.views.AuthorsPerCountryView;

import java.util.List;
import java.util.Optional;

public interface CountryApplicationService {

    List<DisplayCountryDto> listAllCountries();

    Optional<DisplayCountryDto> findCountryById(Long countryId);

    Optional<DisplayCountryDto> create(CreateCountryDto createCountryDto);
    Optional<DisplayCountryDto> update(Long id, CreateCountryDto createCountryDto);

    Optional<DisplayCountryDto> deleteCountry(Long id);

    List<AuthorsPerCountryView> findAllAuthorsByCountry();
    AuthorsPerCountryView findAuthorsByCountry(Long id);

    void refreshMaterializedView();

    //Lab 3 - additional task from school
    List<Country> findByContinent(String continent);


}
