package com.newsahilhotel.dto;

import lombok.*;

@Getter@Setter
@AllArgsConstructor@NoArgsConstructor
@Builder
public class LoginResponseDto {
    private String token;

    private String tokenType;

    private UserResponseDto user;
}
