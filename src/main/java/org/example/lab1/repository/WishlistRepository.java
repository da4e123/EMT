package org.example.lab1.repository;

import org.example.lab1.model.domain.Book;
import org.example.lab1.model.domain.User;
import org.example.lab1.model.domain.Wishlist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface WishlistRepository extends JpaRepository<Wishlist, Long> {
//
//    List <Wishlist> findByUser(User user);
//
    Optional<Wishlist> findByUser(User user);
}
