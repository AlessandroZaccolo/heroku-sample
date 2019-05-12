package tech.bts.herokusample.api;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tech.bts.herokusample.model.User;

@RestController
@RequestMapping(path = "/api/login")
public class LoginApi {

    @PostMapping("/register")
    public String register(@RequestBody User user) {

        return "email: " + user.getEmail() + ", pwd: " + user.getPassword();
    }
}
