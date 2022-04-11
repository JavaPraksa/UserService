package levi9.UserService.api;

import levi9.UserService.dto.UserDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "user-service")
public interface UserServiceApi {
    @GetMapping(value = "/")
    Example1 example();

    @GetMapping(value = "/auth/is-authenticated")
    Boolean isAuthenticated(@RequestParam String token);

    @GetMapping(value = "/user/{username}")
    ResponseEntity<UserDto> getUserByUsername(@PathVariable String username);

    @GetMapping("/user/getEmailById/{id}")
    String getUserEmailById(@PathVariable Long id);
}
