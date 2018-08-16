package ua.demo.service.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ua.demo.service.entity.models.User;
import ua.demo.service.exceptions.NotAvailableEmailException;
import ua.demo.service.repositories.UsersRepository;
import ua.demo.service.services.UsersService;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;


@Service
public class UsersServiceImpl implements UsersService {

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void signUp(User user) {
        String hashPassword = passwordEncoder.encode(user.getHashPassword());

        if(usersRepository.findUserByEmail(user.getEmail()).isPresent()){
            throw new NotAvailableEmailException();
        }
        user.setHashPassword(hashPassword);
        user.setEarnedMoney(BigDecimal.ZERO);

        usersRepository.save(user);
    }

    @Override
    public List<User> findAll() {
        return usersRepository.findAll();
    }

    @Override
    public Optional<User> findOneByEmail(String email) {
        return usersRepository.findUserByEmail(email);
    }

    @Override
    public Optional<User> findOneById(Long id) {
        return usersRepository.findUserById(id);
    }

    @Override
    public User save(User user) {
        return usersRepository.save(user);
    }

}
