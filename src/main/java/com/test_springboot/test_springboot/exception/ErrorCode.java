package com.test_springboot.test_springboot.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

@Getter
@AllArgsConstructor
public enum ErrorCode {
//    TEST(100, "Test", true),
    UNCATEGORIED(500, "UNCATEGORIED", false, HttpStatus.INTERNAL_SERVER_ERROR),
    INVALID_EXCEPTION_KEY(500, "Invalid Exception Key", false, HttpStatus.INTERNAL_SERVER_ERROR),
    USER_EXISTED(400, "User Existed", false, HttpStatus.BAD_REQUEST),
    USER_NOT_EXISTED(400, "User Not Existed", false, HttpStatus.NOT_FOUND),
    INVALID_USERNAME(400, "Username must be at lease {min} character", false, HttpStatus.BAD_REQUEST),
    INVALID_PASSWORD(400, "Password must be at lease {min} character", false, HttpStatus.BAD_REQUEST),
    UNAUTHENTICATED(401, "Unauthenticated", false, HttpStatus.UNAUTHORIZED),
    UNAUTHORIZED(403, "You do not have permission to access data", false, HttpStatus.FORBIDDEN),
    INVALID_DOB(400, "User's age must be at least {min}", false, HttpStatus.BAD_REQUEST),
    ;

    private int code;
    private String message;
    private boolean success;
    private HttpStatusCode httpStatusCode;

}
