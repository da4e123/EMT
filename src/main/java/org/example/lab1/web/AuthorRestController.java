package org.example.lab1.web;


import org.example.lab1.model.Author;
import org.example.lab1.model.dto.AuthorDto;
import org.example.lab1.service.AuthorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/authors")
public class AuthorRestController {
    private final AuthorService authorService;

    public AuthorRestController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping()
    public List<Author> getAllAuthors(){
        return authorService.listAllAuthors();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Author> getAuthorsById(@PathVariable Long id){
        return authorService.findById(id).map(author -> ResponseEntity.ok().body(author)).
                orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/add")
    public ResponseEntity<Author> addAuthor(@RequestBody AuthorDto authorDto){
        return authorService.create(authorDto.getName(), authorDto.getSurname(), authorDto.getCountry())
                .map(author -> ResponseEntity.ok().body(author))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Author> deleteAuthor(@PathVariable Long id){
        return authorService.delete(id).map(author -> ResponseEntity.ok().body(author))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/edit/{id}")
    public ResponseEntity<Author> editAuthor(@PathVariable Long id, @RequestBody AuthorDto authorDto){
        return authorService.update(id, authorDto.getName(), authorDto.getSurname(), authorDto.getCountry())
                .map(author -> ResponseEntity.ok().body(author))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
