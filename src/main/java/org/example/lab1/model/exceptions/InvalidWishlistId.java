package org.example.lab1.model.exceptions;

public class InvalidWishlistId extends RuntimeException{

    public InvalidWishlistId(){
        super("Invalid wishlist id!");
    }
}
