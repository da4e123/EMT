package org.example.lab1.repository;

import org.example.lab1.model.domain.Author;
import org.example.lab1.model.domain.Country;
import org.example.lab1.model.dto.AuthorDto;
import org.example.lab1.model.projections.AuthorProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {

    @Query("select a.name, a.surname from Author a")
    List<AuthorProjection> takeNameAndSurnameByProjection();


    //Lab 3 - additional task from school
    List<AuthorDto> findByCountryId(Long countryId);

}
