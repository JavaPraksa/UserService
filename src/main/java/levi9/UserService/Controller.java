package levi9.UserService;

import levi9.UserService.api.Example1;
import levi9.UserService.api.UserServiceApi;
import levi9.VehicleService.api.VehicleServiceApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller{
    @Autowired
    private VehicleServiceApi vehicleServiceApi;

    @GetMapping(value = "/")
    public Example1 example() {
        return new Example1("aaaaa");
    }

    @GetMapping("/vehicle")
    public String example1(){
        return vehicleServiceApi.example1();
    }
}
