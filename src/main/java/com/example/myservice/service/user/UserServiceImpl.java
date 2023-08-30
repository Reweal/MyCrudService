package com.example.myservice.service.user;

import com.example.myservice.model.Role;
import com.example.myservice.model.User;
import com.example.myservice.model.UserArchive;
import com.example.myservice.repository.RoleRepository;
import com.example.myservice.repository.UserArchiveRepository;
import com.example.myservice.repository.UserRepository;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Data
public class UserServiceImpl implements UserService {

  private RoleRepository roleRepository;
  private UserRepository userRepository;
  private UserArchiveRepository userAchrive;


  public PasswordEncoder bCryptPasswordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Autowired
  public UserServiceImpl(RoleRepository roleRepository, UserRepository userRepository,
      UserArchiveRepository userAchrive) {
    this.roleRepository = roleRepository;
    this.userRepository = userRepository;
    this.userAchrive = userAchrive;
  }

  @Override
  public List<Role> getListRoles() {
    return roleRepository.findAll();
  }

  @Override
  public List<Role> getListByRole(List<String> name) {
    return roleRepository.findAllByRole(name);
  }

  @Override
  @Transactional
  public boolean saveUser(User user) {
    User oldUser = findUserByUsername(user.getUsername());
    if (oldUser != null) {
      return false;
    }
    user.setPassword(bCryptPasswordEncoder().encode(user.getPassword()));
    userRepository.save(user);
    return true;
  }

  @Override
  public Set<User> getSetUsers() {
    return userRepository.findAll().stream().sorted()
        .collect(Collectors.toCollection(LinkedHashSet::new));
  }

  @Override
  @Transactional
  public void deleteUserById(Long id) {
    Optional<User> user = userRepository.findById(id);
    User model = user.orElseThrow();

    UserArchive archive = UserArchive.builder()
        .id(model.getId())
        .firstName(model.getFirstName())
        .lastName(model.getLastName())
        .username(model.getUsername())
        .age(model.getAge())
        .email(model.getEmail())
        .password(model.getPassword())
        .build();

    archivateUser(archive);
    userRepository.deleteById(id);
  }

  @Override
  @Transactional
  public void updateUser(User user) {
    User oldUser = findUserById(user.getId());
    if (oldUser.getPassword().equals(user.getPassword()) || "".equals(user.getPassword())) {
      user.setPassword(oldUser.getPassword());
    } else {
      user.setPassword(bCryptPasswordEncoder().encode(user.getPassword()));
    }
    userRepository.save(user);
  }

  @Override
  public User findUserById(Long id) {
    return userRepository.findById(id).orElseThrow();
  }

  @Override
  public User findUserByUsername(String username) {
    return userRepository.findByUsername(username);
  }

  @Override
  @Transactional(readOnly = true)
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    User user = userRepository.findByUsername(username);
    if (user == null) {
      throw new UsernameNotFoundException(String.format("User '%s' not found", username));
    }
    return user;
  }

  @Override
  @Transactional
  public boolean archivateUser(UserArchive user) {
    User oldUser = findUserByUsername(user.getUsername());
    if (oldUser == null) {
      return false;
    }
    user.setPassword(bCryptPasswordEncoder().encode(user.getPassword()));
    userAchrive.save(user);
    return true;
  }


  @Override
  public UserArchive findUserArchiveByUsername(String username) {
    return userAchrive.findByUsername(username);
  }

  @Override
  public UserArchive findUserArchiveById(Long id) {
    return userAchrive.findById(id).orElseThrow();
  }


  @Override
  public Set<UserArchive> getSetUsersArchive() {
    return userAchrive.findAll().stream().sorted()
        .collect(Collectors.toCollection(LinkedHashSet::new));
  }

  @Override
  @Transactional
  public void unArchivateUser(Long id) {
    Optional<UserArchive> archive = userAchrive.findById(id);
    UserArchive model = archive.orElseThrow();

    User user = User.builder()
        .id(model.getId())
        .firstName(model.getFirstName())
        .lastName(model.getLastName())
        .username(model.getUsername())
        .age(model.getAge())
        .email(model.getEmail())
        .password(model.getPassword())
        .build();

    userRepository.save(user);
    userAchrive.deleteById(id);
  }
}