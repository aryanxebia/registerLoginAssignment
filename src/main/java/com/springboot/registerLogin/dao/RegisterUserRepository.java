package com.springboot.registerLogin.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springboot.registerLogin.entity.Users;
@Repository
public interface RegisterUserRepository extends JpaRepository<Users, Long> {
	Optional<Users> findByEmail(String email);
}
