package levi9.UserService.service.impl;

import levi9.UserService.dto.CredentialsDto;
import levi9.UserService.dto.LoggedUserDto;
import levi9.UserService.model.User;
import levi9.UserService.repository.UserRepository;
import levi9.UserService.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public LoggedUserDto authenticate(CredentialsDto credentials) {
        User user = userRepository.findByUsername(credentials.getUsername());
        if(user != null && user.getPassword().equals(credentials.getPassword())){
            return new LoggedUserDto(null, user.getRole(), user.getUsername());
        }
        return null;
    }
}
