package ua.demo.service.services;

import ua.demo.service.entity.forms.LoginForm;
import ua.demo.service.entity.dto.TokenDto;


public interface LoginService {
    TokenDto login(LoginForm loginForm);
}
