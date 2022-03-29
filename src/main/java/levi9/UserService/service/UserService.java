package levi9.UserService.service;

import levi9.UserService.dto.CredentialsDto;
import levi9.UserService.dto.LoggedUserDto;
import levi9.UserService.model.User;

public interface UserService {
    LoggedUserDto authenticate(CredentialsDto credentials);
}
