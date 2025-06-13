package com.mnghia.dts.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserResponse {
    Integer id;
    String name;
    String username;
    String password;
    String email;
    String phone;
    String avatar;
    String status;
    String role;
    Boolean deleted;
    @JsonProperty(namespace = "deleted_at")
    LocalDate deletedAt;
}
