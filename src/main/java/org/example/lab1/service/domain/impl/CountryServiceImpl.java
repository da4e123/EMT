package org.example.lab1.service.domain.impl;

import org.example.lab1.model.domain.Country;
import org.example.lab1.model.exceptions.InvalidCountryId;
import org.example.lab1.repository.CountryRepository;
import org.example.lab1.service.domain.CountryService;
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
    public Optional<Country> create(Country country) {
        return Optional.of(countryRepository.save(country));
    }

    @Override
    public Optional<Country> update(Long id, Country country) {
        return countryRepository.findById(id).map(existingCountry -> {
            if(country.getName() != null){
                existingCountry.setName(country.getName());
            }
            if (country.getContinent() != null){
                existingCountry.setContinent(country.getContinent());
            }


            return countryRepository.save(existingCountry);
        });


    }

    @Override
    public Optional<Country> deleteCountry(Long id) {
        Country country = findCountryById(id).orElseThrow(InvalidCountryId::new);
        countryRepository.delete(country);
        return Optional.of(country);
    }
}