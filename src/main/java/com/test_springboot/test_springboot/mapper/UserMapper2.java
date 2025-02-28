package com.test_springboot.test_springboot.mapper;

import com.test_springboot.test_springboot.dto.request.UserCreationRequest;
import com.test_springboot.test_springboot.dto.request.UserUpdateRequest;
import com.test_springboot.test_springboot.dto.response.UserResponse;
import com.test_springboot.test_springboot.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UserMapper2 {
    //nhan vao request la UserCreationRequest tra ve User ghi get set gia tri
    User toUser(UserCreationRequest request);
    void updateUser(@MappingTarget User user, UserUpdateRequest userUpdateRequest);

    //link field firstName cua User cho field lastName cua UserResponse
    @Mapping(source = "firstName", target = "lastName")
    @Mapping(target = "username", ignore = true) //khong get set field username
    UserResponse toUserResponse(User user);
}
