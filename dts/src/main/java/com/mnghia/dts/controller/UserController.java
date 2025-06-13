package com.mnghia.dts.controller;

import com.mnghia.dts.dto.request.ApiResponse;
import com.mnghia.dts.dto.request.UserRequest;
import com.mnghia.dts.dto.response.UserListResponse;
import com.mnghia.dts.dto.response.UserResponse;
import com.mnghia.dts.service.impl.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/list")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ApiResponse<UserListResponse> getAll(
            @RequestParam("page") int page,
            @RequestParam("size") int size
    ){
        PageRequest request = PageRequest.of(
                page,
                size, Sort.by("id").ascending()
        );
        Page<UserResponse> users = userService.list(request);
        int totalPage = users.getTotalPages();
        List<UserResponse> userList = users.getContent();
        UserListResponse userListResponse = UserListResponse.builder()
                .totalPages(totalPage)
                .userResponses(userList)
                .build();
        return ApiResponse.<UserListResponse>builder()
                .data(userListResponse)
                .build();
    }

    @PostMapping("/create")
    public ApiResponse<UserResponse> create(
            @RequestBody UserRequest request
    ){
        return ApiResponse.<UserResponse>builder()
                .data(userService.create(request))
                .build();
    }

    @PutMapping("/update/{id}")
    public ApiResponse<UserResponse> update(
            @PathVariable("id") Integer id,
            @RequestBody UserRequest request
            ){
        return ApiResponse.<UserResponse>builder()
                .data(userService.update(id, request))
                .build();
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ApiResponse<UserResponse> delete(@PathVariable("id") Integer id){
        return ApiResponse.<UserResponse>builder()
                .data(userService.delete(id))
                .build();
    }

    @GetMapping("/{id}")
    public ApiResponse<UserResponse> getById(@PathVariable("id") Integer id){
        return ApiResponse.<UserResponse>builder()
                .data(userService.getById(id))
                .build();
    }

    @PutMapping("/upload/{id}")
    public ApiResponse<UserResponse> uploadAvatar(
            @PathVariable("id") Integer id,
            @RequestPart(value = "file", required = false) MultipartFile file
    ){
        return ApiResponse.<UserResponse>builder()
                .data(userService.uploadAvatar(id, file))
                .build();
    }

}
