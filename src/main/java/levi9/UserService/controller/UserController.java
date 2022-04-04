package levi9.UserService.controller;

import levi9.UserService.dto.UserDto;
import levi9.UserService.dto.UserRegistrationDto;
import levi9.UserService.model.User;
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

    //get user by its username
    @GetMapping(value = "/getUser/{username}")
    public ResponseEntity<UserDto> getUserByUsername(@PathVariable String username){
        return new ResponseEntity<>(userService.loadUserByUsername(username), HttpStatus.OK);
    }

    //update user
    @PutMapping("/updateUser/{username}")
    public ResponseEntity<UserDto> updateUser(@PathVariable String username,@RequestBody UserDto userDetails) {
        UserDto userDto = userService.editUserDetails(username,userDetails);
        return ResponseEntity.ok(userDto);
    }


}
