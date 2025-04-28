package org.example.lab1.service.domain.impl;

import org.example.lab1.model.domain.Author;
import org.example.lab1.model.domain.Country;
import org.example.lab1.model.exceptions.InvalidAuthorId;
import org.example.lab1.model.exceptions.InvalidCountryId;
import org.example.lab1.repository.AuthorRepository;
import org.example.lab1.service.domain.CountryService;
import org.example.lab1.service.domain.AuthorService;
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
    public Optional<Author> create(Author author) {
        return Optional.of(authorRepository.save(author));
    }

    @Override
    public Optional<Author> update(Long authorId, Author author) {

        return authorRepository.findById(authorId).map(existingAuthor -> {
                    if(author.getName() != null){
                        existingAuthor.setName(author.getName());
                    }
                    if (author.getSurname() != null){
                        existingAuthor.setSurname(author.getSurname());
                    }

                    if (author.getCountry() != null){
                        existingAuthor.setCountry(author.getCountry());
                    }

                    return authorRepository.save(existingAuthor);


    });
    }




    @Override
    public Optional<Author> delete(Long id) {
        Author author = findById(id).orElseThrow(InvalidAuthorId::new);
        authorRepository.delete(author);
        return Optional.of(author);

    }
}
