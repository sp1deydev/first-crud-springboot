package com.test_springboot.test_springboot.exception;

public enum ErrorCode {
    TEST(100, "Test", true),
    INVALID_EXCEPTION_KEY(500, "Invalid Exception Key", false),
    USER_EXISTED(400, "User Existed", false),
    INVALID_USERNAME(400, "Username must be at lease 3 character", false),
    INVALID_PASSWORD(400, "Password must be at lease 8 character", false),
    ;

    private int code;
    private String message;
    private boolean success ;

    public boolean isSuccess() {
        return success;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    ErrorCode(int code, String message, boolean success) {
        this.code = code;
        this.message = message;
        this.success = success;
    }
}
