package com.example.myservice.service.role;


import com.example.myservice.model.Role;
import java.util.List;

public interface RoleService {

    void saveRole(Role role);

    List<Role> findAll();

    List<Role> findAllById(List<Long> id);

    Role findById(Long id);

}