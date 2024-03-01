package com.mak.ecommerce.userservice.service;

import com.mak.ecommerce.userservice.dtos.SignUpRequestDto;
import com.mak.ecommerce.userservice.dtos.UserDto;
import com.mak.ecommerce.userservice.exception.UserNotFoundException;
import com.mak.ecommerce.userservice.models.User;
import com.mak.ecommerce.userservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserServiceInterface {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository repo){
        this.userRepository = repo;
    }

    @Override
    public UserDto createUser(SignUpRequestDto dto) {
        User newUser = userRepository.save(signUpRequestDtoToUser(dto));
        return transformUserToUserDto(newUser);
    }

    @Override
    public User getUserById(UUID id) throws UserNotFoundException {
        Optional<User> optionalUser = userRepository.findById(id);
        if(optionalUser.isPresent()){
            return optionalUser.get();
        }
        throw new UserNotFoundException("User Not found for id:"+id);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User updateUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public void updatePassword(UUID id, String password) {
        userRepository.updatePassword(id, password);
    }

    private User signUpRequestDtoToUser(SignUpRequestDto dto){
        User newUser = new User();
        newUser.setName(dto.getName());
        newUser.setHashedPassword(dto.getPassword());
        newUser.setEmail(dto.getEmail());
        return newUser;
    }

    private UserDto transformUserToUserDto(User user){
        UserDto dto = new UserDto();
        dto.setName(user.getName());
        dto.setEmail(user.getEmail());
        dto.setId(user.getId());
        return dto;
    }
}
