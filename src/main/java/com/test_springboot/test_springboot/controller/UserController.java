package com.test_springboot.test_springboot.controller;

import com.test_springboot.test_springboot.dto.request.ApiResponse;
import com.test_springboot.test_springboot.dto.request.UserCreationRequest;
import com.test_springboot.test_springboot.dto.request.UserUpdateRequest;
import com.test_springboot.test_springboot.dto.response.UserResponse;
import com.test_springboot.test_springboot.entity.User;
import com.test_springboot.test_springboot.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping
    User createUser(@RequestBody @Valid UserCreationRequest request) {
        return userService.createUser(request);
    }

    @GetMapping
    ApiResponse<List<User>> getUsers() {
        ApiResponse<List<User>> apiResponse = new ApiResponse<>();

        apiResponse.setCode(200);
        apiResponse.setResult(userService.getUsers());
        return apiResponse;
    }
    @GetMapping("/{userId}")
    UserResponse getUser(@PathVariable String userId) {
        return userService.getUser(userId);
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
