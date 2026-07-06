package com.newsahilhotel.exception;

import com.newsahilhotel.dto.StandardApiResponse;
import org.springframework.http.ResponseEntity;

public class BadRequestException extends RuntimeException{
    public BadRequestException(String message){
        super(message);
    }
}

