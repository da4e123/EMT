package org.example.lab1.service.impl;

import org.example.lab1.model.Country;
import org.example.lab1.model.dto.CountryDto;
import org.example.lab1.model.exceptions.InvalidCountryId;
import org.example.lab1.repository.CountryRepository;
import org.example.lab1.service.CountryService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CountryServiceImpl implements CountryService {
    private final CountryRepository countryRepository;

    public CountryServiceImpl(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    @Override
    public List<Country> listAllCountries() {
        return countryRepository.findAll();
    }

    @Override
    public Optional<Country> findCountryById(Long countryId) {
        return Optional.of(countryRepository.findById(countryId).orElseThrow(InvalidCountryId::new));
    }

    @Override
    public Optional<Country> create(String name, String continent) {
        return Optional.of(countryRepository.save(new Country(name,continent)));
    }

    @Override
    public Optional<Country> update(Long id, String name, String continent) {
        Country country = findCountryById(id).orElseThrow(InvalidCountryId::new);
        country.setName(name);
        country.setContinent(continent);
        return Optional.of(countryRepository.save(country));
    }

    @Override
    public Optional<Country> deleteCountry(Long id) {
        Country country = findCountryById(id).orElseThrow(InvalidCountryId::new);
        countryRepository.delete(country);
        return Optional.of(country);
    }
}