package org.example.lab1.web;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.example.lab1.dto.CreateCountryDto;
import org.example.lab1.dto.DisplayCountryDto;
import org.example.lab1.model.domain.Country;
import org.example.lab1.model.dto.CountryDto;
import org.example.lab1.service.application.CountryApplicationService;
import org.example.lab1.service.domain.CountryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/countries")
@Tag(name = "Country API", description = "Endpoints for managing countries")
public class CountryRestController {
    private final CountryApplicationService countryApplicationService;

    public CountryRestController(CountryApplicationService countryApplicationService) {
        this.countryApplicationService = countryApplicationService;
    }

    @Operation(summary = "Get all countries", description = "Retrieves a list of all countries")
    @GetMapping("")
    public List<DisplayCountryDto> getAllCountries(){
        return countryApplicationService.listAllCountries();
    }

    @Operation(summary = "Get a country by ID", description = "Find a country by its ID.")
    @GetMapping("/{id}")
    public ResponseEntity<DisplayCountryDto> getCountryById(@PathVariable Long id){
        return countryApplicationService.findCountryById(id).map(country -> ResponseEntity.ok().body(country))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Add a new country", description = "Create a new country based on the give CountryDto.")
    @PostMapping("/add")
    public ResponseEntity<DisplayCountryDto> addCountry(@RequestBody CreateCountryDto createCountryDto){
        return countryApplicationService.create(createCountryDto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }


    @Operation(summary = "Update an existing country", description = "Updates a country by ID using CountryDto.")
    @PostMapping("/edit/{id}")
    public ResponseEntity<DisplayCountryDto> editCountry(@PathVariable Long id, @RequestBody CreateCountryDto createCountryDto){
        return countryApplicationService.update(id,createCountryDto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Delete an existing country", description = "Delete a country by its ID.")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<DisplayCountryDto> deleteCountry(@PathVariable Long id){
        return countryApplicationService.deleteCountry(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }




}
