package ua.demo.service.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.demo.service.entity.models.User;
import ua.demo.service.services.UsersService;

import java.util.List;

import static ua.demo.service.entity.dto.UserDto.from;


@RestController
public class UsersController {


    @Autowired
    private UsersService usersService;

    /*@GetMapping("/users")
    public List<User> getUsers() {
        return usersService.findAll();
    }

    @GetMapping("/users/{user-id}")
    public User getUser(@PathVariable("user-id") Long userId) {
        return usersService.findOneById(userId).orElseThrow(IllegalArgumentException::new);
    }*/

    @PostMapping("/signup")
    public ResponseEntity<Object> addUser(@RequestBody User user) {
        usersService.signUp(user);
        return ResponseEntity.ok().build();
    }
}
