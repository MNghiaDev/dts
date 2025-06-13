package com.mnghia.dts.service.impl;

import com.mnghia.dts.dto.request.UserRequest;
import com.mnghia.dts.dto.response.UserResponse;
import com.mnghia.dts.exception.AppException;
import com.mnghia.dts.exception.ErrorCode;
import com.mnghia.dts.mapper.UserMapper;
import com.mnghia.dts.model.User;
import com.mnghia.dts.repository.UserRepository;
import com.mnghia.dts.service.IImageService;
import com.mnghia.dts.service.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.lang.Integer;
import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class UserService implements IUserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final IImageService imageService;

    @Override
    public Page<UserResponse> list(PageRequest request) {
        return userRepository.findAllByDeletedFalse(request).map(userMapper::toUserResponse);
    }

    @Override
    public UserResponse create(UserRequest request) {
        if(userRepository.existsByEmail(request.getEmail())) {
            throw  new AppException(ErrorCode.EMAIL_EXISTED);
        }
        if(userRepository.existsByPhone(request.getPhone())){
            throw new AppException(ErrorCode.PHONE_EXISTED);
        }
        if(userRepository.existsByUsername(request.getUsername())){
            throw new AppException(ErrorCode.USERNAME_EXISTED);
        }
        User user = userMapper.toUser(request);
        user.setDeleted(false);
        return userMapper.toUserResponse(userRepository.save(user));
    }

    @Override
    public UserResponse update(Integer id, UserRequest request) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED));
        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setPhone(request.getPhone());
        return userMapper.toUserResponse(userRepository.save(user));
    }

    @Override
    public UserResponse delete(Integer id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED));

        user.setDeleted(true);
        user.setDeletedAt(LocalDate.now());
        return userMapper.toUserResponse(userRepository.save(user));
    }

    @Override
    public UserResponse getById(Integer id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED));
        return userMapper.toUserResponse(user);
    }

    @Override
    public UserResponse uploadAvatar(Integer id, MultipartFile file){
        User user = userRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED));
        if (file != null && !file.isEmpty()) {
            user.setAvatar(imageService.uploadImage(file));
        }
        return userMapper.toUserResponse(userRepository.save(user));
    }
}
