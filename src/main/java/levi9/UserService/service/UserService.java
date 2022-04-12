package levi9.UserService.service;

import levi9.UserService.dto.CredentialsDto;
import levi9.UserService.dto.UserDto;
import levi9.UserService.dto.UserRegistrationDto;

import javax.servlet.http.HttpSession;

public interface UserService {
    UserDto authenticate(CredentialsDto credentials, HttpSession session);

    UserDto register(UserRegistrationDto newUser);


    UserDto loadUserByUsername(String username);

    UserDto loadUserById(Long id);

    UserDto editUserDetails(String username,UserDto userDetails);
}
