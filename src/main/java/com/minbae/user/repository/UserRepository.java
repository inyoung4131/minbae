package com.minbae.user.repository;

import com.minbae.user.entity.User;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface UserRepository extends JpaRepository<User,Long> {

    @EntityGraph(attributePaths = "user")
    Optional<User> findByUserEmailAndUserPwd(String email,String pwd);
}
