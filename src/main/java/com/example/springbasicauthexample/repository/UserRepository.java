package com.example.springbasicauthexample.repository;

import com.example.springbasicauthexample.entity.User;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {

    @EntityGraph(attributePaths = {"roles"})
    Optional<User> findByUserName(String userName);

}
