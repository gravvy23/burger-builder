package com.example.burgerbuilder.repository;

import com.example.burgerbuilder.model.User;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}