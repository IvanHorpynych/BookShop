package ua.demo.service.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.demo.service.entity.models.User;

import java.util.List;
import java.util.Optional;


public interface UsersRepository extends JpaRepository<User, Long> {


    List<User> findAllByFirstName(String firstName);

    Optional<User> findUserByLogin(String login);

    Optional<User> findUserById(Long id);
}
