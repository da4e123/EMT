package org.example.lab1.web;


import org.example.lab1.model.Book;
import org.example.lab1.model.dto.BookDto;
import org.example.lab1.service.BookService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BookRestController {

    private final BookService bookService;


    public BookRestController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("")
    public List<Book> getAllBooks(){
        return bookService.listAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable Long id){
        return bookService.findById(id).map(book -> ResponseEntity.ok().body(book))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/add")
    public ResponseEntity<Book> addBook(@RequestBody BookDto bookDto){
        return bookService.create(bookDto.getName(),bookDto.getCategory(), bookDto.getAuthor(), bookDto.getAvailableCopies())
                .map(book -> ResponseEntity.ok().body(book))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Book> deleteBook(@PathVariable Long id){
        return bookService.delete(id).map(book -> ResponseEntity.ok().body(book))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/edit/{id}")
    public ResponseEntity<Book> editBook(@PathVariable Long id, @RequestBody BookDto bookDto){
        return bookService.update(id,bookDto.getName(),bookDto.getCategory(),bookDto.getAuthor(),bookDto.availableCopies)
                .map(book -> ResponseEntity.ok().body(book))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/getcopy/{id}")
    public ResponseEntity<Void> getCopy(@PathVariable Long id){
        bookService.getCopy(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/addcopy/{id}")
    public ResponseEntity<Void> addCopy(@PathVariable Long id){
        bookService.returnCopy(id);
        return ResponseEntity.ok().build();

    }

    @GetMapping("/filter")
    public ResponseEntity<List<Book>> filterByCategory(@RequestParam String category){
        List<Book> filteredBooks = bookService.filterByCategory(category);

        return ResponseEntity.ok(filteredBooks);
    }


}
