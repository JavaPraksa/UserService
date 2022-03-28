package levi9.UserService;

import levi9.UserService.api.Example1;
import levi9.UserService.api.UserServiceApi;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller implements UserServiceApi {

    @Override
    public Example1 example() {
        return new Example1("aaaaa");
    }
}
