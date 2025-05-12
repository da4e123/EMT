package org.example.lab1.repository;

import org.example.lab1.model.domain.Author;
import org.example.lab1.model.domain.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    //Lab 3 - additional task from school
    Book searchBookByName(String name);

}
