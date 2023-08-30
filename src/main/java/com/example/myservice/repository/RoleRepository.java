package com.example.myservice.repository;

import com.example.myservice.model.Role;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    @Query("select r from Role r where r.role = :role")
    Role findByRole(@Param("role") String role);

    @Query("select u from Role u where u.role in (:name)")
    List<Role> findAllByRole(@Param("name") List<String> name);

    @Query("select u from Role u")
    List<Role> findAll();
}
