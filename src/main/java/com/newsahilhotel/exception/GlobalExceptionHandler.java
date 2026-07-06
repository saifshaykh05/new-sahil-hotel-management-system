package com.newsahilhotel.exception;

import com.newsahilhotel.dto.StandardApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.http.StreamingHttpOutputMessage;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.management.ObjectName;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(Exception.class)
    public ResponseEntity<StandardApiResponse<Object>> handleException(Exception exception){
        StandardApiResponse<Object> response= new StandardApiResponse<>(
                false,exception.getMessage(),null
        );
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<StandardApiResponse<Object>> handleResourceNotFoundException(ResourceNotFoundException exe){
        StandardApiResponse<Object> response=new StandardApiResponse<>(
                false,exe.getMessage(),null
        );
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }
    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<StandardApiResponse<Object>> handleBadRequestException(BadRequestException exe){
        StandardApiResponse<Object> response=new StandardApiResponse<>(
                false,exe.getMessage(),null
        );
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }
}
