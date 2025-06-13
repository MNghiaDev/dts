package com.mnghia.dts.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

@Getter
public enum ErrorCode {
    UNCATEGORIZED_EXCEPTION(9999, "Uncategorized error", HttpStatus.INTERNAL_SERVER_ERROR),
    INVALID_KEY(1001, "Uncategorized error", HttpStatus.BAD_REQUEST),
    USER_EXISTED(1002, "User existed", HttpStatus.BAD_REQUEST),
    USER_NOT_EXISTED(1003, "User not existed", HttpStatus.NOT_FOUND),
    UNAUTHENTICATED(1004, "Unauthenticated", HttpStatus.UNAUTHORIZED),
    UNAUTHORIZED(1005, "You do not have permission", HttpStatus.FORBIDDEN),
    INVALID_EMAIL(1006, "Email must be valid", HttpStatus.BAD_REQUEST),
    EMAIL_EXISTED(1007, "Email existed", HttpStatus.BAD_REQUEST),
    USERNAME_EXISTED(1008, "Username existed", HttpStatus.BAD_REQUEST),
    PHONE_EXISTED(1009, "Phone existed", HttpStatus.BAD_REQUEST),
    ERROR_USERNAME_OR_PASSWORD(1010, "Sai tên đăng nhập hoặc mật khẩu", HttpStatus.BAD_REQUEST),
    ;

    ErrorCode(int code, String message, HttpStatusCode statusCode) {
        this.code = code;
        this.message = message;
        this.statusCode = statusCode;
    }

    private final int code;
    private final String message;
    private final HttpStatusCode statusCode;
}
