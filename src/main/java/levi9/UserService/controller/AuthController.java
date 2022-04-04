package levi9.UserService.controller;

import levi9.UserService.dto.CredentialsDto;
import levi9.UserService.dto.UserDto;
import levi9.UserService.filter.SimpleAuthFilter;
import levi9.UserService.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.UUID;

@RestController
@RequestMapping(value = "/auth", produces = MediaType.APPLICATION_JSON_VALUE)
public class AuthController {
    private final UserService userService;
    private final SimpleAuthFilter simpleAuthFilter;

    @Autowired
    public AuthController(UserService userService, SimpleAuthFilter simpleAuthFilter) {
        this.userService = userService;
        this.simpleAuthFilter = simpleAuthFilter;
    }

    @PostMapping("/login")
    public ResponseEntity<Object> login(HttpSession session, @RequestBody CredentialsDto credentials) {
        return ResponseEntity.ok(userService.authenticate(credentials, session));
    }

    @PutMapping("/logout")
    public ResponseEntity<Object> logout(HttpSession session) {
        session.invalidate();
        return ResponseEntity.ok().build();
    }

    @GetMapping(value = "/is-authenticated")
    public Boolean isAuthenticated(@RequestParam String token) {
        return simpleAuthFilter.isTokenValidInAnySession(token);
    }

}
