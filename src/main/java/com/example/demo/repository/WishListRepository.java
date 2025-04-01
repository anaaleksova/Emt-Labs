package com.example.demo.repository;

import com.example.demo.model.domain.User;
import com.example.demo.model.domain.WishList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface WishListRepository extends JpaRepository<WishList, Long> {
    Optional<WishList> findByUser(User user);
    Optional<WishList> findByUserUsername(String username);
}
