package com.example.myservice.repository;

import com.example.myservice.model.User;
import com.example.myservice.model.UserArchive;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserArchiveRepository extends JpaRepository<UserArchive, Long> {
  @Query("select u from UserArchive u where u.username = :username")
  UserArchive findByUsername(@Param("username") String username);

  @Query("select u from UserArchive u where u.id = :id")
  Optional<UserArchive> findById(@Param("id") Long id);

  @Query("select u from User u join fetch u.roles")
  List<UserArchive> findAll();
}
