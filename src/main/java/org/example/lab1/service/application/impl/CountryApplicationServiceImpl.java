package org.example.lab1.service.application.impl;

import org.example.lab1.dto.CreateCountryDto;
import org.example.lab1.dto.DisplayCountryDto;
import org.example.lab1.model.domain.Country;
import org.example.lab1.model.views.AuthorsPerCountryView;
import org.example.lab1.repository.AuthorsPerCountryViewRepository;
import org.example.lab1.service.application.CountryApplicationService;
import org.example.lab1.service.domain.CountryService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CountryApplicationServiceImpl implements CountryApplicationService {

    private final CountryService countryService;
    private final AuthorsPerCountryViewRepository authorsPerCountryViewRepository;

    public CountryApplicationServiceImpl(CountryService countryService, AuthorsPerCountryViewRepository authorsPerCountryViewRepository) {
        this.countryService = countryService;

        this.authorsPerCountryViewRepository = authorsPerCountryViewRepository;
    }

    @Override
    public List<DisplayCountryDto> listAllCountries() {
        return DisplayCountryDto.from(countryService.listAllCountries());
    }

    @Override
    public Optional<DisplayCountryDto> findCountryById(Long countryId) {
        return countryService.findCountryById(countryId).map(DisplayCountryDto::from);
    }


    @Override
    public Optional<DisplayCountryDto> create(CreateCountryDto createCountryDto) {
        return countryService.create(createCountryDto.toCountry()).map(DisplayCountryDto::from);
    }

    @Override
    public Optional<DisplayCountryDto> update(Long id, CreateCountryDto createCountryDto) {
        return countryService.update(id,createCountryDto.toCountry()).map(DisplayCountryDto::from);
    }

    @Override
    public Optional<DisplayCountryDto> deleteCountry(Long id) {
        return countryService.deleteCountry(id).map(DisplayCountryDto::from);
    }

    @Override
    public List<AuthorsPerCountryView> findAllAuthorsByCountry() {
        return authorsPerCountryViewRepository.findAll();
    }

    @Override
    public AuthorsPerCountryView findAuthorsByCountry(Long id) {
        return authorsPerCountryViewRepository.findById(id).orElseThrow();
    }

    @Override
    public void refreshMaterializedView() {
        this.authorsPerCountryViewRepository.refreshMaterializedView();

    }

    //Lab 3 - additional task from school
    @Override
    public List<Country> findByContinent(String continent) {
        return countryService.findByContinent(continent);
    }
}
