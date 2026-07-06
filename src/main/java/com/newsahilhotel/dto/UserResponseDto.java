package com.newsahilhotel.dto;

import com.newsahilhotel.enums.Role;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserResponseDto {
    private Long id;

    private String fullName;

    private String email;

    private String phoneNumber;

    private Role role;

    private Boolean active;
}
