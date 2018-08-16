package ua.demo.service.services;

import ua.demo.service.entity.models.User;

import java.util.List;
import java.util.Optional;


public interface UsersService {

    void signUp(User user);

    List<User> findAll();

    Optional<User> findOneByEmail(String email);

    Optional<User> findOneById(Long id);

    User save(User user);
}
