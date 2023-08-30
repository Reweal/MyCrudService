package com.example.myservice.util;

import com.example.myservice.model.Role;
import com.example.myservice.model.User;
import com.example.myservice.service.role.RoleService;
import com.example.myservice.service.user.UserService;
import java.util.Collections;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements ApplicationListener<ApplicationReadyEvent> {
    private final RoleService roleService;
    private final UserService userService;

    public DataLoader(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        User admin = new User();
        admin.setFirstName("Admin");
        admin.setLastName("Admin");
        admin.setEmail("Admin@admin");
        admin.setAge(30);
        admin.setUsername("admin");
        admin.setPassword("admin"); // password is "admin"

        Role adminRole = new Role();
        adminRole.setRole("ROLE_ADMIN");
        roleService.saveRole(adminRole);

        admin.setRoles(Collections.singleton(adminRole));

        User user = new User();
        user.setFirstName("User");
        user.setLastName("User");
        user.setEmail("user@user");
        user.setAge(25);
        user.setUsername("user");
        user.setPassword("user"); // password is "user"

        Role userRole = new Role();
        userRole.setRole("ROLE_USER");
        roleService.saveRole(userRole);

        user.setRoles(Collections.singleton(userRole));

        userService.saveUser(admin);
        userService.saveUser(user);
    }
}

