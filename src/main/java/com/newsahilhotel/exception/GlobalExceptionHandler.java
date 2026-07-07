package com.newsahilhotel.exception;

import com.newsahilhotel.dto.StdApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(Exception.class)
    public ResponseEntity<StdApiResponse<Object>> handleException(Exception exception){
        StdApiResponse<Object> response= new StdApiResponse<>(
                false,exception.getMessage(),null
        );
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<StdApiResponse<Object>> handleResourceNotFoundException(ResourceNotFoundException exe){
        StdApiResponse<Object> response=new StdApiResponse<>(
                false,exe.getMessage(),null
        );
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }
    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<StdApiResponse<Object>> handleBadRequestException(BadRequestException exe){
        StdApiResponse<Object> response=new StdApiResponse<>(
                false,exe.getMessage(),null
        );
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }
}
