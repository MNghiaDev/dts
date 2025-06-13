package com.mnghia.dts.dto.request;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserRequest {
    String name;
    String username;
    String password;
    String email;
    String phone;
    String avatar;
    String status;
}
