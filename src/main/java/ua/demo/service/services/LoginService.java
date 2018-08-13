package ua.demo.service.services;

import ua.demo.service.forms.LoginForm;
import ua.demo.service.transfer.TokenDto;


public interface LoginService {
    TokenDto login(LoginForm loginForm);
}
