package com.mnghia.dts.mapper;

import com.mnghia.dts.dto.request.UserRequest;
import com.mnghia.dts.dto.response.UserResponse;
import com.mnghia.dts.enums.Role;
import com.mnghia.dts.enums.StatusUser;
import com.mnghia.dts.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserMapper {
    private final PasswordEncoder passwordEncoder;

    public UserResponse toUserResponse(User user){
        return UserResponse.builder()
                .id(user.getId())
                .name(user.getName())
                .username(user.getUsername())
                .password("********")
                .email(user.getEmail())
                .phone(user.getEmail())
                .avatar(user.getAvatar())
                .status(String.valueOf(user.getStatus()))
                .role(String.valueOf(user.getRole()))
                .deleted(user.getDeleted())
                .deletedAt(user.getDeletedAt())
                .build();
    }

    public User toUser(UserRequest request){
        return User.builder()
                .name(request.getName())
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .email(request.getEmail())
                .phone(request.getEmail())
                .status(StatusUser.ACTIVE)
                .role(Role.USER)
                .build();
    }
}
