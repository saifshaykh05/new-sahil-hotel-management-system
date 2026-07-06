package com.newsahilhotel.service;

import com.newsahilhotel.dto.UserRequestDto;
import com.newsahilhotel.dto.UserResponseDto;
import com.newsahilhotel.dto.UserUpdateDto;
import com.newsahilhotel.entity.User;
import com.newsahilhotel.exception.BadRequestException;
import com.newsahilhotel.exception.ResourceNotFoundException;
import com.newsahilhotel.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.management.relation.Role;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    final private UserRepository userRepository;
    final private PasswordEncoder passwordEncoder;
    public UserServiceImpl(UserRepository userRepository,PasswordEncoder passwordEncoder){
        this.userRepository=userRepository;
        this.passwordEncoder=passwordEncoder;
    }
    public UserResponseDto createUser(UserRequestDto requestdto) {
        if(userRepository.existsByEmail(requestdto.getEmail())){
            throw new BadRequestException("Email already exists.");
        }
        if(userRepository.existsByPhoneNumber(requestdto.getPhoneNumber())){
            throw new BadRequestException("PhoneNumver already exists. ");
        }
        User user = User.builder()
                .fullName(requestdto.getFullName())
                .email(requestdto.getEmail())
                .phoneNumber(requestdto.getPhoneNumber())
                .role(requestdto.getRole())
                .password(passwordEncoder.encode(requestdto.getPassword()))
                .build();
        User saveduser=userRepository.save(user);
        UserResponseDto responseDto=new UserResponseDto(saveduser.getId(), saveduser.getFullName(), saveduser.getEmail(), saveduser.getPhoneNumber(), saveduser.getRole(),saveduser.getActive());
        return responseDto;
    }

    @Override
    public UserResponseDto getUserById(Long id) {
        User user=userRepository.findById(id).orElseThrow(
                ()-> new ResourceNotFoundException("User Not Found")
        );
        return new UserResponseDto(user.getId(), user.getFullName(), user.getEmail(), user.getPhoneNumber(),user.getRole(),user.getActive());
    }

    @Override
    public List<UserResponseDto> getAllUsers() {
        List<User> users=userRepository.findAll();
        List<UserResponseDto> responseDtos=new ArrayList<>();
        for(User user:users){
            responseDtos.add( new UserResponseDto(user.getId(), user.getFullName(), user.getEmail(), user.getPhoneNumber(),user.getRole(),user.getActive()));
        }
        return responseDtos;
    }

    @Override
    public List<UserResponseDto> getUsersByRole(Role role) {
        List<User> users=userRepository.findByRole(role);
        List<UserResponseDto> responseDtos=new ArrayList<>();
        for(User user:users){
            responseDtos.add( new UserResponseDto(user.getId(), user.getFullName(), user.getEmail(), user.getPhoneNumber(),user.getRole(),user.getActive()));
        }
        return responseDtos;
    }

    @Override
    public void deleteUser(Long userId) {
        User user=userRepository.findById(userId).orElseThrow(
                ()-> new ResourceNotFoundException("user not found")
        );
        userRepository.delete(user);
    }

    @Override
    public void activateUser(Long userId) {
        User user=userRepository.findById(userId).orElseThrow(
                ()-> new ResourceNotFoundException("user not found")
        );
        user.setActive(true);
    }

    @Override
    public void deactivateUser(Long userId) {
        User user=userRepository.findById(userId).orElseThrow(
                ()-> new ResourceNotFoundException("user not found")
        );
        user.setActive(false);
    }

    @Override
    @Transactional
    public UserResponseDto updateUser(Long id, UserUpdateDto requestDto) {

        User user = userRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("User not found with id: " + id));

        // Update Full Name
        if (requestDto.getFullName() != null &&
                !requestDto.getFullName().isBlank()) {
            user.setFullName(requestDto.getFullName());
        }

        // Update Email
        if (requestDto.getEmail() != null &&
                !requestDto.getEmail().isBlank() &&
                !requestDto.getEmail().equals(user.getEmail())) {

            if (userRepository.existsByEmail(requestDto.getEmail())) {
                throw new BadRequestException(
                        "Email already exists: " + requestDto.getEmail());
            }

            user.setEmail(requestDto.getEmail());
        }

        // Update Phone Number
        if (requestDto.getPhoneNumber() != null &&
                !requestDto.getPhoneNumber().isBlank() &&
                !requestDto.getPhoneNumber().equals(user.getPhoneNumber())) {

            if (userRepository.existsByPhoneNumber(requestDto.getPhoneNumber())) {
                throw new BadRequestException(
                        "Phone number already exists: " + requestDto.getPhoneNumber());
            }

            user.setPhoneNumber(requestDto.getPhoneNumber());
        }

        // Update Password
        if (requestDto.getPassword() != null &&
                !requestDto.getPassword().isBlank()) {

            // If your entity has setPassword(), replace this line accordingly.
            user.setPassword(passwordEncoder.encode(requestDto.getPassword()));
        }

        // Update Role
        if (requestDto.getRole() != null) {
            user.setRole(requestDto.getRole());
        }

        User updatedUser = userRepository.save(user);

        return new UserResponseDto(
                updatedUser.getId(),
                updatedUser.getFullName(),
                updatedUser.getEmail(),
                updatedUser.getPhoneNumber(),
                updatedUser.getRole(),
                updatedUser.getActive()
        );
    }
}
