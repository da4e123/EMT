package org.example.lab1.dto;

import org.example.lab1.model.domain.Author;
import org.example.lab1.model.domain.Book;
import org.example.lab1.model.domain.Wishlist;
import org.example.lab1.model.dto.BookDto;

import java.util.List;
import java.util.stream.Collectors;

public record WishlistDto(Long id, DisplayUserDto displayUserDto, List<DisplayBookDto> books ) {

    public static WishlistDto from(Wishlist wishlist){
        return new WishlistDto(
                wishlist.getId(),
                DisplayUserDto.from(wishlist.getUser()),
                DisplayBookDto.from(wishlist.getBooks())
        );
    }

    public static List<WishlistDto> from(List<Wishlist> wishlistDtos){
        return wishlistDtos.stream().map(WishlistDto::from).collect(Collectors.toList());
    }






}
