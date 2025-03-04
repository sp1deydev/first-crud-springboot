package com.test_springboot.test_springboot.mapper;

import com.test_springboot.test_springboot.dto.request.RoleRequest;
import com.test_springboot.test_springboot.dto.response.RoleResponse;
import com.test_springboot.test_springboot.entity.Role;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface RoleMapper {
    @Mapping(target = "permissions", ignore = true)
    Role toRole(RoleRequest request);

    RoleResponse toRoleResponse(Role role);
}
