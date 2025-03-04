package com.test_springboot.test_springboot.mapper;

import com.test_springboot.test_springboot.dto.request.PermissionRequest;
import com.test_springboot.test_springboot.dto.response.PermissionResponse;
import com.test_springboot.test_springboot.entity.Permission;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PermissionMapper {
    Permission toPermission(PermissionRequest request);
    PermissionResponse toPermissionResponse(Permission permission);
}
