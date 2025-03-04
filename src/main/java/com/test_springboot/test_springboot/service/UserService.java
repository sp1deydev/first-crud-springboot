package com.test_springboot.test_springboot.service;

import com.test_springboot.test_springboot.dto.request.UserCreationRequest;
import com.test_springboot.test_springboot.dto.request.UserUpdateRequest;
import com.test_springboot.test_springboot.dto.response.UserResponse;
import com.test_springboot.test_springboot.entity.User;
import com.test_springboot.test_springboot.enums.Role;
import com.test_springboot.test_springboot.exception.AppException;
import com.test_springboot.test_springboot.exception.ErrorCode;
import com.test_springboot.test_springboot.mapper.UserMapper;
import com.test_springboot.test_springboot.repository.RoleRepository;
import com.test_springboot.test_springboot.repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;

@Service
//de bo autowired khai bao ngay 1 instance
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class UserService {
    UserRepository userRepository;
    RoleRepository roleRepository;
    UserMapper userMapper;
    PasswordEncoder passwordEncoder;

    public User createUser(UserCreationRequest request) {

        if(userRepository.existsByUsername(request.getUsername())) {
            throw new AppException(ErrorCode.USER_EXISTED);
//            throw new RuntimeException("ABC");
        }
//        EXAMPLE ABOUT BUILDER IN LOMBOK
//        UserCreationRequest userCreationRequest = UserCreationRequest.builder()
//                .username("builder")
//                .password("22222222")
//                .build();

        User user = userMapper.toUser(request);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        HashSet<String> roles = new HashSet<>();
        roles.add(Role.USER.name());
//        user.setRoles(roles);
        return userRepository.save(user);
    }
    @PreAuthorize("hasRole('ADMIN')") //kiem tra truoc khi vao method
    public List<User> getUsers() {
        log.info("In method get Users");
        return userRepository.findAll();
    }
    @PostAuthorize("returnObject.username == authentication.name") //thuc hien xong ham roi moi kiem tra
    public UserResponse getUser(String id) {
        return userMapper.toUserResponse(userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User Not Found!")));
    }
    public UserResponse getInfo() {
        var context = SecurityContextHolder.getContext();
        log.info("context: {}", context);
        String name = context.getAuthentication().getName();

        User user = userRepository.findByUsername(name).orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED));
        return userMapper.toUserResponse(user);
    }
    public User updateUser(String userId, UserUpdateRequest request) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User Not Found!"));
        //mapping roi update vao user vi user la mapping target
        userMapper.updateUser(user, request);
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        var roles = roleRepository.findAllById(request.getRoles());
        user.setRoles(new HashSet<>(roles));

        return userRepository.save(user);
    }

    public void deleteUser(String userId) {
        userRepository.deleteById(userId);
    }
}
