package org.example.lab1.web;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.example.lab1.dto.CreateAuthorDto;
import org.example.lab1.dto.DisplayAuthorDto;
import org.example.lab1.model.domain.Author;
import org.example.lab1.model.dto.AuthorDto;
import org.example.lab1.service.application.AuthorApplicationService;
import org.example.lab1.service.domain.AuthorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/authors")
@Tag(name = "Author API", description = "Endpoints for managing authors")
public class AuthorRestController {
    private final AuthorApplicationService authorApplicationService;

    public AuthorRestController(AuthorApplicationService authorApplicationService) {
        this.authorApplicationService = authorApplicationService;
    }


    @Operation(summary = "Get all authors", description = "Retrieves a list of all authors")
    @GetMapping()
    public List<DisplayAuthorDto> getAllAuthors(){
        return authorApplicationService.listAllAuthors();
    }

    @Operation(summary = "Get a author by ID", description = "Find a author by its ID.")
    @GetMapping("/{id}")
    public ResponseEntity<DisplayAuthorDto> getAuthorsById(@PathVariable Long id){
        return authorApplicationService.findById(id).map(author -> ResponseEntity.ok().body(author)).
                orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Add a new author", description = "Create a new author based on the give AuthorDto.")
    @PostMapping("/add")
    public ResponseEntity<DisplayAuthorDto> addAuthor(@RequestBody CreateAuthorDto createAuthorDto){
        return authorApplicationService.create(createAuthorDto)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
    @Operation(summary = "Update an existing author", description = "Updates a author by ID using AuthorDto.")
    @PostMapping("/edit/{id}")
    public ResponseEntity<DisplayAuthorDto> editAuthor(@PathVariable Long id, @RequestBody CreateAuthorDto createAuthorDto){
        return authorApplicationService.update(id, createAuthorDto)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Delete an existing author", description = "Delete a author by its ID.")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteAuthor(@PathVariable Long id){
        if (authorApplicationService.findById(id).isPresent()){
            authorApplicationService.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }


}
