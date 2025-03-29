package org.example.lab1.model.dto;

import org.example.lab1.model.enums.Category;
import lombok.Data;
@Data
public class BookDto {
    public String name;
    public Category category;
    public Long author;
    public Integer availableCopies;

    public String getName() {
        return name;
    }

    public Category getCategory() {
        return category;
    }

    public Long getAuthor() {
        return author;
    }

    public Integer getAvailableCopies() {
        return availableCopies;
    }
}
