package com.test_springboot.test_springboot.controller;
import com.oracle.svm.core.annotate.Delete;
import com.test_springboot.test_springboot.dto.request.ApiResponse;
import com.test_springboot.test_springboot.dto.request.PermissionRequest;
import com.test_springboot.test_springboot.dto.response.PermissionResponse;
import com.test_springboot.test_springboot.service.PermissionService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/permissions")
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PermissionController {
    PermissionService permissionService;

    @PostMapping
    ApiResponse<PermissionResponse> create(@RequestBody PermissionRequest request) {
        log.info("Create Permission Controller");
        return ApiResponse.<PermissionResponse>builder()
                .result(permissionService.create(request))
                .build();
    }

    @GetMapping
    ApiResponse<List<PermissionResponse>> getAll() {
        return ApiResponse.<List<PermissionResponse>>builder()
                .result(permissionService.getAll())
                .build();
    }
    @DeleteMapping("/{permission}")
    ApiResponse delete(@PathVariable String permission) {
        log.warn("Delete Permission: {}", permission);
        permissionService.delete(permission);
        return ApiResponse.builder()
                .message("Deleted")
                .build();
    }
}
