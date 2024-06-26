package com.springboot.registerLogin.dao;


import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.springboot.registerLogin.entity.Users;
@Repository
public interface RegisterUserRepository extends JpaRepository<Users, Long> {

}
