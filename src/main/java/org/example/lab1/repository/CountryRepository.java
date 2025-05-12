package org.example.lab1.repository;

import org.example.lab1.model.domain.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface CountryRepository extends JpaRepository<Country, Long> {

    //Lab 3 - additional task from school
    List<Country> findByContinent(String continent);
}
