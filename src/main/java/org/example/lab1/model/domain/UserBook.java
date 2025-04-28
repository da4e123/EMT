package org.example.lab1.model.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class UserBook {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    Long book_id;
    String username;

    public UserBook(Long book_id, String username) {
        this.book_id = book_id;
        this.username = username;
    }

    public UserBook() {

    }

    public void setBook_id(Long book_id) {
        this.book_id = book_id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Long getBook_id() {
        return book_id;
    }

    public String getUsername() {
        return username;
    }
}
