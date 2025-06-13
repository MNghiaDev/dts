package com.mnghia.dts.model;

import com.mnghia.dts.enums.Role;
import com.mnghia.dts.enums.StatusUser;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    String name;
    String username;
    String password;
    String email;
    String phone;
    String avatar;
    @Enumerated(EnumType.STRING)
    StatusUser status;
    @Enumerated(EnumType.STRING)
    Role role;
    Boolean deleted;
    @Column(name = "deleted_at")
    LocalDate deletedAt;
}
