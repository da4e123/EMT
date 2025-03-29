//package org.example.lab1.config;
//
//import jakarta.annotation.PostConstruct;
//import org.example.lab1.model.Author;
//import org.example.lab1.model.Country;
//import org.example.lab1.repository.AuthorRepository;
//import org.example.lab1.repository.BookRepository;
//import org.example.lab1.repository.CountryRepository;
//import org.springframework.stereotype.Component;
//
//
//@Component
//public class DataInitializer {
//    private final AuthorRepository authorRepository;
//    private final CountryRepository countryRepository;
//
//    private final BookRepository bookRepository;
//
//
//    public DataInitializer(AuthorRepository authorRepository, CountryRepository countryRepository, BookRepository bookRepository) {
//        this.authorRepository = authorRepository;
//        this.countryRepository = countryRepository;
//        this.bookRepository = bookRepository;
//    }
//
//    @PostConstruct
//    public void init(){
//        countryRepository.save(new Country("France", "Europe"));
//        countryRepository.save(new Country("Germany", "Europe"));
//        countryRepository.save(new Country("Spain", "Europe"));
//
//        authorRepository.save(new Author("Miguel de","Servantes",))
//    }
//
//
//}
