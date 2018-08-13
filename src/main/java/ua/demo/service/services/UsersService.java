package ua.demo.service.services;

import ua.demo.service.forms.UserForm;
import ua.demo.service.models.User;

import java.util.List;



public interface UsersService {
    void signUp(UserForm userForm);

    List<User> findAll();

    User findOne(Long userId);
}
