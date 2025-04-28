package org.example.lab1.service.application.impl;

import org.example.lab1.dto.CreateCountryDto;
import org.example.lab1.dto.DisplayCountryDto;
import org.example.lab1.service.application.CountryApplicationService;
import org.example.lab1.service.domain.CountryService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CountryApplicationServiceImpl implements CountryApplicationService {

    public final CountryService countryService;

    public CountryApplicationServiceImpl(CountryService countryService) {
        this.countryService = countryService;
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
}
