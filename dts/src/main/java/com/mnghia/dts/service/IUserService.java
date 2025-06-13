package com.mnghia.dts.service;

import com.mnghia.dts.dto.request.UserRequest;
import com.mnghia.dts.dto.response.UserResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.multipart.MultipartFile;

import java.lang.Integer;

public interface IUserService {
    Page<UserResponse> list(PageRequest request);

    UserResponse create(UserRequest request);

    UserResponse update(Integer id, UserRequest request);

    UserResponse delete(Integer id);

    UserResponse getById(Integer id);

    UserResponse uploadAvatar(Integer id, MultipartFile file);
}
