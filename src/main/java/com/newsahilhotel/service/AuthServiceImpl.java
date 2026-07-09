package com.newsahilhotel.service;

import com.newsahilhotel.dto.LoginRequestDto;
import com.newsahilhotel.dto.LoginResponseDto;
import com.newsahilhotel.dto.UserResponseDto;
import com.newsahilhotel.entity.User;
import com.newsahilhotel.repository.UserRepository;
import com.newsahilhotel.security.CustomUserDetails;
import com.newsahilhotel.security.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService{
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    @Override
    public LoginResponseDto login(LoginRequestDto requestDto) {
        UsernamePasswordAuthenticationToken authenticationToken=new UsernamePasswordAuthenticationToken(
          requestDto.getEmail(),requestDto.getPassword()
        );
        Authentication authentication=authenticationManager.authenticate(authenticationToken);
        CustomUserDetails userDetails=(CustomUserDetails)authentication.getPrincipal();
        User user=userDetails.getUser();
        String token = jwtService.generateJwtToken(userDetails);
        return mapUserToResponse(user,token);
    }





    private LoginResponseDto mapUserToResponse(User user,String token){
        UserResponseDto userResponseDto=new UserResponseDto(user.getId(), user.getFullName(), user.getEmail(), user.getPhoneNumber(),user.getRole(),user.getActive());
        LoginResponseDto responseDto=new LoginResponseDto(
          token,"Bearer", userResponseDto
        );
        return responseDto;
    }
}
