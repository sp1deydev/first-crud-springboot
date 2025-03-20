package com.test_springboot.test_springboot.controller;

import com.test_springboot.test_springboot.dto.request.ApiResponse;
import com.test_springboot.test_springboot.dto.request.UserCreationRequest;
import com.test_springboot.test_springboot.dto.request.UserUpdateRequest;
import com.test_springboot.test_springboot.dto.response.UserResponse;
import com.test_springboot.test_springboot.entity.User;
import com.test_springboot.test_springboot.service.UserService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@Slf4j
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping
    User createUser(@RequestBody @Valid UserCreationRequest request) {
        return userService.createUser(request);
    }

    @GetMapping
    ApiResponse<List<UserResponse>> getUsers() {
        var authentication = SecurityContextHolder.getContext().getAuthentication();
        log.info("Username: {}", authentication.getName());
        authentication.getAuthorities().forEach(grantedAuthority -> log.info(grantedAuthority.getAuthority()
        ));
        return ApiResponse.<List<UserResponse>>builder()
                .result(userService.getUsers())
                .build();
    }
    @GetMapping("/{userId}")
    UserResponse getUser(@PathVariable String userId) {
        return userService.getUser(userId);
    }
    @GetMapping("/info")
    ApiResponse<UserResponse> getInfo() {
        return ApiResponse.<UserResponse>builder()
                .result(userService.getInfo())
                .build();
    }

    @PutMapping("/{userId}")
    User updateUser(@PathVariable String userId, @RequestBody UserUpdateRequest request) {
        return userService.updateUser(userId, request);
    }
    @DeleteMapping("/{userId}")
    String deleteUser(@PathVariable String userId) {
        userService.deleteUser(userId);
        return "User was deleted";
    }
}
