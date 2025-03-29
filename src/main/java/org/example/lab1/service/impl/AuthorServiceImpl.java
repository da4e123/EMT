package org.example.lab1.service.impl;

import org.example.lab1.model.Author;
import org.example.lab1.model.Country;
import org.example.lab1.model.exceptions.InvalidAuthorId;
import org.example.lab1.model.exceptions.InvalidCountryId;
import org.example.lab1.repository.AuthorRepository;
import org.example.lab1.service.AuthorService;
import org.example.lab1.service.CountryService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class AuthorServiceImpl implements AuthorService {
    private final AuthorRepository authorRepository;
    private final CountryService countryService;

    public AuthorServiceImpl(AuthorRepository authorRepository, CountryService countryService) {
        this.authorRepository = authorRepository;
        this.countryService = countryService;
    }

    @Override
    public List<Author> listAllAuthors() {
        return authorRepository.findAll();
    }

    @Override
    public Optional<Author> findById(Long id) {
        return Optional.of(authorRepository.findById(id).orElseThrow(InvalidAuthorId::new));
    }

    @Override
    public Optional<Author> create(String name, String surname, Long countryId) {
        Country country = countryService.findCountryById(countryId).orElseThrow(InvalidCountryId::new);
        return Optional.of(authorRepository.save(new Author(name,surname,country)));
    }

    @Override
    public Optional<Author> update(Long authorId, String name, String surname, Long countryId) {
        Author author = findById(authorId).orElseThrow(InvalidAuthorId::new);
        Country country = countryService.findCountryById(countryId).orElseThrow(InvalidCountryId::new);
        author.setName(name);
        author.setSurname(surname);
        author.setCountry(country);
        return Optional.of(authorRepository.save(author));
    }

    @Override
    public Optional<Author> delete(Long id) {
        Author author = findById(id).orElseThrow(InvalidAuthorId::new);
        authorRepository.delete(author);
        return Optional.of(author);

    }
}
