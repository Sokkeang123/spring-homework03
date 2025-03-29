package org.example._14_sieb_sokkeang_pp_web_homework003.exception;

import org.example._14_sieb_sokkeang_pp_web_homework003.model.response.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiResponse<String>> handleResourceNotFoundException(ResourceNotFoundException ex) {
        ApiResponse<String> response = ApiResponse.<String>builder()
                .message(ex.getMessage())
                .payload(null)
                .status(HttpStatus.NOT_FOUND)
                .timestamp(LocalDateTime.now())
                .build();
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ApiResponse<String>> handleIllegalArgumentException(IllegalArgumentException ex) {
        ApiResponse<String> response = ApiResponse.<String>builder()
                .message(ex.getMessage())
                .payload(null)
                .status(HttpStatus.BAD_REQUEST)
                .timestamp(LocalDateTime.now())
                .build();
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<String>> handleGenericException(Exception ex) {
        ApiResponse<String> response = ApiResponse.<String>builder()
                .message("An unexpected error occurred: " + ex.getMessage())
                .payload(null)
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .timestamp(LocalDateTime.now())
                .build();
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}