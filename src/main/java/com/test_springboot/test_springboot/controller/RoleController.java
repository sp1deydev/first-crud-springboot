package com.test_springboot.test_springboot.controller;
import com.test_springboot.test_springboot.dto.request.ApiResponse;
import com.test_springboot.test_springboot.dto.request.RoleRequest;
import com.test_springboot.test_springboot.dto.response.RoleResponse;
import com.test_springboot.test_springboot.service.RoleService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/roles")
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class RoleController {
    RoleService roleService;

    @PostMapping
    ApiResponse<RoleResponse> create(@RequestBody RoleRequest request) {
        return ApiResponse.<RoleResponse>builder()
                .result(roleService.create(request))
                .build();
    }

    @GetMapping
    ApiResponse<List<RoleResponse>> getAll() {
        return ApiResponse.<List<RoleResponse>>builder()
                .result(roleService.getAll())
                .build();
    }
    @DeleteMapping("/{role}")
    ApiResponse delete(@PathVariable String role) {
        log.warn("Delete Role: {}", role);
        roleService.delete(role);
        return ApiResponse.builder()
                .message("Deleted")
                .build();
    }
}
