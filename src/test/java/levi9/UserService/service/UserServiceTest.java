package levi9.UserService.service;

import levi9.UserService.dto.CredentialsDto;
import levi9.UserService.dto.UserRegistrationDto;
import levi9.UserService.exception.BadRequestException;
import levi9.UserService.model.User;
import levi9.UserService.model.enums.UserRole;
import levi9.UserService.repository.UserRepository;
import levi9.UserService.service.impl.UserServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.junit4.SpringRunner;

import javax.servlet.http.HttpSession;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTest {
    @Mock
    UserRepository userRepository;

    @InjectMocks
    UserServiceImpl userService;

    @Test
    public void testUserAuthentication(){
        User user1 = User.builder().username("testUser1").password("testPassword1").role(UserRole.CLIENT).build();
        CredentialsDto okCredentials = CredentialsDto.builder().username("testUser1").password("testPassword1").build();
        CredentialsDto wrongUsernameCredentials = CredentialsDto.builder().username("wrongUsername").password("testPassword1").build();
        CredentialsDto wrongPasswordCredentials = CredentialsDto.builder().username("testUser1").password("wrongPassword").build();
        HttpSession session = new MockHttpSession();
        String exceptionMessage = "Wrong username or password";

        when(userRepository.findByUsername("testUser1")).thenReturn(user1);

        assertThat(userService.authenticate(okCredentials, session)).hasFieldOrPropertyWithValue("role", UserRole.CLIENT).hasFieldOrPropertyWithValue("username", "testUser1");
        Throwable wrongUsernameException = assertThrows(BadRequestException.class, () -> userService.authenticate(wrongUsernameCredentials, session));
        assertEquals(exceptionMessage, wrongUsernameException.getMessage());
        Throwable wrongPasswordException = assertThrows(BadRequestException.class, () -> userService.authenticate(wrongPasswordCredentials, session));
        assertEquals(exceptionMessage, wrongPasswordException.getMessage());
    }

    @Test
    public void testUserRegistrationPasswordNotMatch(){
        UserRegistrationDto passwordNotMatchUser = UserRegistrationDto.builder().password("newUser").confirmPassword("wrongPassword").username("newUser").build();
        String exceptionMessage = "Passwords not match";

        Throwable passwordNotMatchException = assertThrows(BadRequestException.class, () -> userService.register(passwordNotMatchUser));
        assertEquals(exceptionMessage, passwordNotMatchException.getMessage());
    }

    @Test
    public void testExistsUsernameRegistration(){
        UserRegistrationDto existsUser = UserRegistrationDto.builder().password("newUser").confirmPassword("newUser").username("newUser").build();
        String exceptionMessage = "Username already exists";

        when(userRepository.findByUsername(existsUser.getUsername())).thenReturn(User.builder().build());

        Throwable passwordNotMatchException = assertThrows(BadRequestException.class, () -> userService.register(existsUser));
        assertEquals(exceptionMessage, passwordNotMatchException.getMessage());
    }
}
