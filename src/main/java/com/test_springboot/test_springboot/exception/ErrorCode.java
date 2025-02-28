package com.test_springboot.test_springboot.exception;

public enum ErrorCode {
    USER_EXISTED(400, "User Existed", false),
    TEST(100, "Test", true),
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
