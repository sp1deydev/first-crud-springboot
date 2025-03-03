package com.test_springboot.test_springboot.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    TEST(100, "Test", true),
    INVALID_EXCEPTION_KEY(500, "Invalid Exception Key", false),
    USER_EXISTED(400, "User Existed", false),
    USER_NOT_EXISTED(400, "User Not Existed", false),
    INVALID_USERNAME(400, "Username must be at lease 3 character", false),
    INVALID_PASSWORD(400, "Password must be at lease 8 character", false),
    UNAUTHENTICATED(401, "Unauthenticated", false),
    ;

    private int code;
    private String message;
    private boolean success;

}
