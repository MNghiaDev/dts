package com.mnghia.dts.exception;

import com.mnghia.dts.dto.request.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    private static final String MIN_ATTRIBUTE = "min";

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ApiResponse<?>> handlingRuntimeException(RuntimeException ex) {
        ApiResponse<?> response = ApiResponse.builder()
                .message(ex.getMessage())
                .code(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .build();
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }

//    @ExceptionHandler(value = MethodArgumentNotValidException.class)
//    ResponseEntity<String> HandlingValidation(MethodArgumentNotValidException exception) {
//        return ResponseEntity.badRequest().body(exception.getDetailMessageCode());
//    }


    private String mapAttribute(String message, Map<String, Object> attributes) {
        String minValue = String.valueOf(attributes.getOrDefault(MIN_ATTRIBUTE, "0"));
        return message.replace("{" + MIN_ATTRIBUTE + "}", minValue);
    }

    @ExceptionHandler(AppException.class)
    public ResponseEntity<ApiResponse<String>> handleAppException(AppException ex) {
        ApiResponse<String> response = ApiResponse.<String>builder()
                .code(ex.getErrorCode().getCode())
                .message(ex.getMessage()) // ✅ trả message cụ thể như "Author not found!"
                .errors(new HashMap<>())
                .data(null)
                .build();

        return ResponseEntity.status(ex.getErrorCode().getStatusCode().value()).body(response);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<?>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();

        ex.getBindingResult().getFieldErrors().forEach(error -> {
            String fieldName = error.getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });

        ApiResponse<?> response = ApiResponse.builder()
                .code(HttpStatus.BAD_REQUEST.value())
                .message("Validation error")
                .errors(errors)
                .data(null)
                .build();

        return ResponseEntity.badRequest().body(response);
    }

}
