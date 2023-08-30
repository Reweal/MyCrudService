package com.example.myservice.repository;

import com.example.myservice.model.User;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Query("select u from User u join fetch u.roles where u.username = :username")
    User findByUsername(@Param("username") String username);

    @Query("select u from User u join fetch u.roles where u.id = :id")
    Optional<User> findById(@Param("id") Long id);

    @Query("select u from User u join fetch u.roles")
    List<User> findAll();
}