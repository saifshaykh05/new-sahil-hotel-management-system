package com.newsahilhotel.service;

import com.newsahilhotel.dto.UserRequestDto;
import com.newsahilhotel.dto.UserResponseDto;
import com.newsahilhotel.dto.UserUpdateDto;

import javax.management.relation.Role;
import java.util.List;

public interface UserService {
    public UserResponseDto createUser(UserRequestDto requestdto);
    public UserResponseDto getUserById(Long id);
    public List<UserResponseDto> getAllUsers();
    public void deactivateUser(Long userId);
    public void activateUser(Long userId);
    public void deleteUser(Long userId);
    List<UserResponseDto> getUsersByRole(Role role);
    UserResponseDto updateUser(Long id, UserUpdateDto requestdto);
}
