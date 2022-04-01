package levi9.UserService.service.impl;

import levi9.UserService.dto.CredentialsDto;
import levi9.UserService.dto.UserDto;
import levi9.UserService.dto.UserRegistrationDto;
import levi9.UserService.exception.BadRequestException;
import levi9.UserService.model.User;
import levi9.UserService.model.enums.UserRole;
import levi9.UserService.repository.UserRepository;
import levi9.UserService.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final ModelMapper mapper;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
        this.mapper = new ModelMapper();
    }

    @Override
    public UserDto authenticate(CredentialsDto credentials, HttpSession session) {
        User user = userRepository.findByUsername(credentials.getUsername());
        if (user == null || !user.getPassword().equals(credentials.getPassword())) {
            throw new BadRequestException("Wrong username or password");
        }
        UUID token = UUID.randomUUID();
        session.setAttribute("token", token);
        session.setAttribute("user", user);
        return UserDto.builder().id(user.getId()).role(user.getRole()).username(user.getUsername()).token(token.toString()).build();
    }

    @Override
    public UserDto register(UserRegistrationDto newUser) {
        if (newUser.passwordsNotEqual()) {
            throw new BadRequestException("Passwords not match");
        }
        if (userRepository.findByUsername(newUser.getUsername()) != null) {
            throw new BadRequestException("Username already exists");
        }
        User user = mapper.map(newUser, User.class);
        user.setRole(UserRole.CLIENT);
        return mapper.map(userRepository.save(user), UserDto.class);
    }
}
