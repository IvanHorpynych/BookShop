package ua.demo.service.services;

import ua.demo.service.entity.models.Token;
import ua.demo.service.entity.models.User;

import java.util.Optional;

public interface TokenService {

    Optional<Token> findByValue(String tokenValue);

    void deleteAllByUser(User user);
}
