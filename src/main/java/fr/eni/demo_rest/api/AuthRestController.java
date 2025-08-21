package fr.eni.demo_rest.api;

import fr.eni.demo_rest.service.AuthService;
import io.jsonwebtoken.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class AuthRestController {

    private final AuthService authService;

    public AuthRestController(AuthService authService) {
        this.authService = authService;
    }

    @GetMapping("api/create-token")
    public String createToken(){
        return authService.createToken();
    }

    @GetMapping("api/check-token")
    public String checkToken(@RequestHeader(value = "Authorization", required = true) String token){
      return authService.checkToken(token);
    }
}
