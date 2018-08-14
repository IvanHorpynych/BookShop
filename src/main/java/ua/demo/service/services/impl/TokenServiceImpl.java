package ua.demo.service.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.demo.service.entity.models.Token;
import ua.demo.service.entity.models.User;
import ua.demo.service.repositories.TokensRepository;
import ua.demo.service.services.TokenService;

import javax.transaction.Transactional;
import java.util.Optional;
@Service
public class  TokenServiceImpl implements TokenService {

    @Autowired
    TokensRepository tokensRepository;

    @Override
    public Optional<Token> findByValue(String tokenValue) {
        return tokensRepository.findOneByValue(tokenValue);
    }

    @Override
    @Transactional
    public void deleteAllByUser(User user) {
        tokensRepository.deleteAllByUser(user);
    }
}
