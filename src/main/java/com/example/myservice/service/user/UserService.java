package com.example.myservice.service.user;

import com.example.myservice.model.Role;
import com.example.myservice.model.User;
import com.example.myservice.model.UserArchive;
import java.util.List;
import java.util.Set;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    List<Role> getListRoles();
    List<Role> getListByRole(List<String> name);
    boolean saveUser(User user);
    Set<User> getSetUsers();
    void deleteUserById(Long id);
    void updateUser(User user);
    User findUserById(Long id);
    User findUserByUsername(String username);
    boolean archivateUser(UserArchive user);
    UserArchive findUserArchiveByUsername(String username);
    UserArchive findUserArchiveById(Long id);
    Set<UserArchive> getSetUsersArchive();
    void unArchivateUser(Long id);
}
