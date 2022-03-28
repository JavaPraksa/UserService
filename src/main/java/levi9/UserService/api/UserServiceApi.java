package levi9.UserService.api;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "user-service")
public interface UserServiceApi {
    @GetMapping(value = "/")
    Example1 example();
}
