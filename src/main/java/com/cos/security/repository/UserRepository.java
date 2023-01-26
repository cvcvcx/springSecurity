package com.cos.security.repository;

import com.cos.security.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Integer> {

    //findBy는 규칙 ->JPA 문법
    // select * from user where username = 1?(parameter username)
    public User findByUsername(String username); //jpa query methods
}
