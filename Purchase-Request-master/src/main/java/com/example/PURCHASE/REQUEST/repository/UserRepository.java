package com.example.PURCHASE.REQUEST.repository;


import com.example.PURCHASE.REQUEST.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByEmail(String email);

}
