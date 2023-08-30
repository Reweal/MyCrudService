package com.example.myservice.model;

import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "USER_ARCHIVE")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserArchive {

  @Id
  @Column(name = "ID", nullable = false, length = 36)
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Setter(AccessLevel.NONE)
  private Long id;

  @Column(name = "FIRSTNAME")
  private String firstName;

  @Column(name = "LASTNAME")
  private String lastName;

  @Column(name = "AGE")
  private Integer age;

  @Column(name = "EMAIL")
  private String email;

  @Column(unique = true, name = "USERNAME")
  private String username;

  @Column(name = "PASSWORD")
  private String password;
}
