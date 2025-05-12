package org.example.lab1.service.application;


import org.example.lab1.dto.CreateBookDto;
import org.example.lab1.dto.DisplayBookDto;
import org.example.lab1.model.domain.Book;
import org.example.lab1.model.enums.Category;

import java.util.List;
import java.util.Optional;

public interface BookApplicationService {
    List<DisplayBookDto> listAll();

    Optional<DisplayBookDto> findById(Long id);

    Optional<DisplayBookDto> create(CreateBookDto createBookDto);
    Optional<DisplayBookDto> update(Long id, CreateBookDto createBookDto);

    void deleteById(Long id);

    List<DisplayBookDto> filterByCategory(String category);

    void getCopy(Long id);
    void returnCopy(Long id);

    //Lab 3 - additional task from school
    Book searchBookByName(String name);
}
