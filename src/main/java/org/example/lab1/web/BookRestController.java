package org.example.lab1.web;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.example.lab1.dto.CreateBookDto;
import org.example.lab1.dto.DisplayBookDto;
import org.example.lab1.model.domain.Book;
import org.example.lab1.model.dto.BookDto;
import org.example.lab1.service.application.AuthorApplicationService;
import org.example.lab1.service.application.BookApplicationService;
import org.example.lab1.service.domain.BookService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
@Tag(name = "Book API", description = "Endpoints for managing books")
public class BookRestController {

    private final BookApplicationService bookApplicationService;
    private final AuthorApplicationService authorApplicationService;

    public BookRestController(BookApplicationService bookApplicationService, AuthorApplicationService authorApplicationService) {
        this.bookApplicationService = bookApplicationService;
        this.authorApplicationService = authorApplicationService;
    }

    @Operation(summary = "Get all books", description = "Retrieves a list of all books.")
    @GetMapping("")

    public List<DisplayBookDto> getAllBooks(){
        return bookApplicationService.listAll();
    }


    @Operation(summary = "Get a book by ID", description = "Find a book by its ID.")
    @GetMapping("/{id}")

    public ResponseEntity<DisplayBookDto> getBookById(@PathVariable Long id){
        return bookApplicationService.findById(id).map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Add a new book", description = "Create a new book based on the give BookDto.")
    @PostMapping("/add")

    public ResponseEntity<DisplayBookDto> addBook(@RequestBody CreateBookDto createBookDto){
        return bookApplicationService.create(createBookDto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Update an existing book", description = "Updates a book by ID using BookDto.")
    @PostMapping("/edit/{id}")

    public ResponseEntity<DisplayBookDto> editBook(@PathVariable Long id, @RequestBody CreateBookDto createBookDto){
        return bookApplicationService.update(id,createBookDto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }


    @Operation(summary = "Delete an existing book", description = "Delete a book by its ID.")
    @DeleteMapping("/delete/{id}")

    public ResponseEntity<Void> deleteBook(@PathVariable Long id){
        if (bookApplicationService.findById(id).isPresent()){
            bookApplicationService.deleteById(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }



    @PostMapping("/getcopy/{id}")
    public ResponseEntity<Void> getCopy(@PathVariable Long id){
        bookApplicationService.getCopy(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/addcopy/{id}")
    public ResponseEntity<Void> addCopy(@PathVariable Long id){
        bookApplicationService.returnCopy(id);
        return ResponseEntity.ok().build();

    }


    @GetMapping("/filter")
    public ResponseEntity<List<DisplayBookDto>> filterByCategory(@RequestParam String category){
        List<DisplayBookDto> filteredBooks = bookApplicationService.filterByCategory(category);
        return ResponseEntity.ok(filteredBooks);
    }



    @Operation(description = "List number of books per author for every author")
    @GetMapping("/by-author")
    public ResponseEntity<?> findAllNumbersOfBooksPerAuthor(){
        return ResponseEntity.status(HttpStatus.OK).body(authorApplicationService.findAllBooksPerAuthor());
    }

    @Operation(description = "List number of books per author for a given author")
    @GetMapping("/by-author/{id}")
    public ResponseEntity<?> findNumberOfBooksPerAuthor(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK).body(authorApplicationService.findBookPerAuthor(id));
    }

    //Lab 3 - additional task from school
    @GetMapping("/search{name}")
    public ResponseEntity<?> searchBookByName(@PathVariable String name){
        return ResponseEntity.status(HttpStatus.OK).body(bookApplicationService.searchBookByName(name));
    }



}
