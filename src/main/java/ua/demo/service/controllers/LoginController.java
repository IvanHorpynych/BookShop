package ua.demo.service.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.demo.service.entity.forms.LoginForm;
import ua.demo.service.entity.models.Token;
import ua.demo.service.services.LoginService;
import ua.demo.service.services.TokenService;
import ua.demo.service.entity.dto.TokenDto;

import java.util.Optional;


@RestController
public class LoginController {

    @Autowired
    private LoginService loginService;

    @Autowired
    private TokenService tokenService;


    @PostMapping("/login")
    public ResponseEntity<TokenDto> login(@RequestBody LoginForm loginForm) {
        return ResponseEntity.ok(loginService.login(loginForm));
    }

    @DeleteMapping("/logout")
    public ResponseEntity<Object> logout(@RequestHeader("token") TokenDto tokenDto) {

        Optional<Token> tokenCandidate = tokenService.findByValue(tokenDto.getValue());

        if (tokenCandidate.isPresent()) {
            tokenService.deleteAllByUser(tokenCandidate.get().getUser());
            return ResponseEntity.ok("Correct logout");
        }
        return ResponseEntity.badRequest().build();
    }
}
