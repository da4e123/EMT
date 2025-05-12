package org.example.lab1.model.domain;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Wishlist {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @ManyToOne
    private User user;

    @ManyToMany
    private List<Book> books;

    public Wishlist(User user) {

        this.user = user;
        this.books = new ArrayList<>();
    }

    public Wishlist(){

    }

    public Long getId() {
        return id;
    }

    public List<Book> getBooks() {
        return books;
    }

    public User getUser() {
        return user;
    }


}
