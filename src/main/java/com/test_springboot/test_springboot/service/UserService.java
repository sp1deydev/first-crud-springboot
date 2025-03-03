package com.test_springboot.test_springboot.service;

import com.test_springboot.test_springboot.dto.request.UserCreationRequest;
import com.test_springboot.test_springboot.dto.request.UserUpdateRequest;
import com.test_springboot.test_springboot.dto.response.UserResponse;
import com.test_springboot.test_springboot.entity.User;
import com.test_springboot.test_springboot.exception.AppException;
import com.test_springboot.test_springboot.exception.ErrorCode;
import com.test_springboot.test_springboot.mapper.UserMapper;
import com.test_springboot.test_springboot.repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

//de bo autowired khai bao ngay 1 instance
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserService {
    UserRepository userRepository;
    UserMapper userMapper;

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
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    public List<User> getUsers() {
        return userRepository.findAll();
    }
    public UserResponse getUser(String id) {
        return userMapper.toUserResponse(userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User Not Found!")));
    }
    public User updateUser(String userId, UserUpdateRequest request) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User Not Found!"));

        //mapping roi update vao user vi user la mapping target
        userMapper.updateUser(user, request);

        return userRepository.save(user);
    }

    public void deleteUser(String userId) {
        userRepository.deleteById(userId);
    }
}
