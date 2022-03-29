package levi9.UserService.controller;

import levi9.UserService.api.Example1;
import levi9.UserService.filter.SimpleAuthFilter;
import levi9.UserService.model.User;
import levi9.VehicleService.api.VehicleServiceApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

//zanemariti, provera da li radi feign i discovery server
@RestController
public class CheckController {
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


    @GetMapping("/check")
    public String check(HttpServletRequest request) {
        if(SimpleAuthFilter.isTokenValidInCurrentSession(request))
            return "true";
        return "false";
    }
}
