package com.example.myservice.controller;

import com.example.myservice.model.Role;
import com.example.myservice.model.User;
import com.example.myservice.service.role.RoleService;
import com.example.myservice.service.user.UserService;
import java.util.List;
import java.util.Set;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@org.springframework.web.bind.annotation.RestController
@RequestMapping("/api")
@Log4j2
public class RestController {

    private UserService userService;
    private RoleService roleService;

    @Autowired
    public RestController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }
    @GetMapping("/users")
    public ResponseEntity<Set<User>> showAllUsers() {
        log.info("method executed: showAllUsers from RestController");
        Set<User> users = userService.getSetUsers();
        if (users == null && users.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<UserDetails> showUser(@PathVariable Long id) {
        log.info("method executed: showUser from RestController");
        UserDetails user = userService.loadUserByUsername(userService.findUserById(id).getUsername());
        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PostMapping("/users")
    public ResponseEntity<User> addNewUser(@RequestBody User user) {
        log.info("method executed: addNewUser from RestController");
        userService.saveUser(user);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<User> updateUser(@RequestBody User user) {
        log.info("method executed: updateUser from RestController");
        userService.updateUser(user);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable("id") Long id) {
        log.info("method executed: deleteUser from RestController");
        userService.deleteUserById(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/viewUser")
    public ResponseEntity<User> showUser(Authentication auth) {
        log.info("method executed: showUser from RestController");
        return new ResponseEntity<>((User) auth.getPrincipal(), HttpStatus.OK);
    }

    @GetMapping("/roles")
    public ResponseEntity<List<Role>> getAllRoles() {
        log.info("method executed: getAllRoles from RestController");
        return new ResponseEntity<>(userService.getListRoles(), HttpStatus.OK);
    }

    @GetMapping("/roles/{id}")
    ResponseEntity<Role> getRoleById(@PathVariable("id") Long id) {
        log.info("method executed: getRoleById from RestController");
        return new ResponseEntity<>(roleService.findById(id), HttpStatus.OK);
    }
}
