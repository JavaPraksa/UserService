package levi9.UserService.api;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "user-service")
public interface UserServiceApi {
    @GetMapping(value = "/")
    Example1 example();

    @GetMapping(value = "/auth/isAuthenticated")
    Boolean isAuthenticated(@RequestParam String token);
}
