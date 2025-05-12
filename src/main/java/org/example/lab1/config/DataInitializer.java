//package org.example.lab1.config;
//
//import jakarta.annotation.PostConstruct;
//import org.example.lab1.model.domain.Author;
//import org.example.lab1.model.domain.Country;
//import org.example.lab1.model.domain.User;
//import org.example.lab1.model.enums.Role;
//import org.example.lab1.repository.AuthorRepository;
//import org.example.lab1.repository.BookRepository;
//import org.example.lab1.repository.CountryRepository;
//import org.example.lab1.repository.UserRepository;
//import org.springframework.stereotype.Component;
//import org.springframework.security.crypto.password.PasswordEncoder;
//
//
//@Component
//public class DataInitializer {
//    private final AuthorRepository authorRepository;
//    private final CountryRepository countryRepository;
//    private final UserRepository userRepository;
//
//    private final BookRepository bookRepository;
//
//    private final PasswordEncoder passwordEncoder;
//
//
//    public DataInitializer(AuthorRepository authorRepository, CountryRepository countryRepository, UserRepository userRepository, BookRepository bookRepository, PasswordEncoder passwordEncoder) {
//        this.authorRepository = authorRepository;
//        this.countryRepository = countryRepository;
//        this.userRepository = userRepository;
//        this.bookRepository = bookRepository;
//        this.passwordEncoder = passwordEncoder;
//    }
//
////    @PostConstruct
//    public void init(){
////        countryRepository.save(new Country("France", "Europe"));
////        countryRepository.save(new Country("Germany", "Europe"));
////        countryRepository.save(new Country("Spain", "Europe"));
////
////        authorRepository.save(new Author("Miguel de","Servantes",))
//        userRepository.save(new User(
//                "db",
//                passwordEncoder.encode("db"),
//                "Dalibor",
//                "Blazevski",
//                Role.ROLE_LIBRARIAN
//        ));
//
//        userRepository.save(new User(
//                "user",
//                passwordEncoder.encode("user"),
//                "user",
//                "user",
//                Role.ROLE_USER
//        ));
//
//
//    }
//
//
//}
