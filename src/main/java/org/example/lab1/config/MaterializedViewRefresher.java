package org.example.lab1.config;

import jakarta.annotation.PostConstruct;
import org.example.lab1.repository.AuthorsPerCountryViewRepository;
import org.example.lab1.repository.BooksPerAuthorViewRepository;
import org.springframework.stereotype.Component;


@Component
public class MaterializedViewRefresher {
    private final AuthorsPerCountryViewRepository authorsPerCountryViewRepository;
    private final BooksPerAuthorViewRepository booksPerAuthorViewRepository;


    public MaterializedViewRefresher(AuthorsPerCountryViewRepository authorsPerCountryViewRepository, BooksPerAuthorViewRepository booksPerAuthorViewRepository) {
        this.authorsPerCountryViewRepository = authorsPerCountryViewRepository;
        this.booksPerAuthorViewRepository = booksPerAuthorViewRepository;
    }

    @PostConstruct
    public void init(){
        booksPerAuthorViewRepository.refreshMaterializedView();;
        authorsPerCountryViewRepository.refreshMaterializedView();
    }
}
