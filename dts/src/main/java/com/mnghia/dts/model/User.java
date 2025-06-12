package com.mnghia.dts.model;

import com.mnghia.dts.enums.StatusUser;
import jakarta.persistence.*;
import jdk.jfr.Name;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Value;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Entity
@Data
@Table(name = "users")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    Integer id;
    String username;
    String password;
    String email;
    String phone;
    String avatar;
    StatusUser status;
    Boolean deleted;
    @Column(name = "deleted_at")
    LocalDate deletedAt;
}
