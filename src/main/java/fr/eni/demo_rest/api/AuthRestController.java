package fr.eni.demo_rest.api;

import fr.eni.demo_rest.service.AuthService;
import io.jsonwebtoken.*;
import org.springframework.web.bind.annotation.*;

class LoginRequest {
    public String email;
    public String password;
}

@RestController
public class AuthRestController {

    private final AuthService authService;

    public AuthRestController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("api/create-token")
    public String createToken(@RequestBody LoginRequest loginRequest){
        return authService.createToken(loginRequest.email, loginRequest.password);
    }

    @GetMapping("api/check-token")
    public String checkToken(@RequestHeader(value = "Authorization", required = true) String token){
      return authService.checkToken(token);
    }
}
