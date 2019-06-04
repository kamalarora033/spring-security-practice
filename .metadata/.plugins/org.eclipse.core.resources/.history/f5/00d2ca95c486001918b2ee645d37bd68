package com.spring.security.practice.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.security.practice.entity.Users;

public interface UserRepositoryDAO extends JpaRepository<Users, Integer> {

	Optional<Users> findByUsername(String username);

}
