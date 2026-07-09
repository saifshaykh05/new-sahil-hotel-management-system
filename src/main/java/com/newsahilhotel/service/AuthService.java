package com.newsahilhotel.service;

import com.newsahilhotel.dto.LoginRequestDto;
import com.newsahilhotel.dto.LoginResponseDto;
import com.newsahilhotel.dto.UserResponseDto;

public interface AuthService {
    LoginResponseDto login(LoginRequestDto requestDto);
    UserResponseDto register(Registe request);
}
