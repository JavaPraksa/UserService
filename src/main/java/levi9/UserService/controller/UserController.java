package levi9.UserService.controller;

import levi9.UserService.dto.UserDto;
import levi9.UserService.dto.UserRegistrationDto;
import levi9.UserService.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/user", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<UserDto> register(@RequestBody UserRegistrationDto newUser) {
        return ResponseEntity.ok(userService.register(newUser));
    }


    @GetMapping(value = "/{username}")
    public ResponseEntity<UserDto> getUserByUsername(@PathVariable String username){
        System.out.println(userService.loadUserByUsername(username));
        return new ResponseEntity<>(userService.loadUserByUsername(username), HttpStatus.OK);
    }



}
