package ua.demo.service.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.demo.service.entity.models.User;
import java.util.List;
import java.util.Optional;


public interface UsersRepository extends JpaRepository<User, Long> {


    List<User> findAllByFirstName(String firstName);

    Optional<User> findUserByLogin(String login);

    Optional<User> findUserById(Long id);

    /*@Modifying
    @Query(value = "update bookshop.fix_user set earned_money = :earnedMoney where id =:id", nativeQuery = true)
    void updateEarnedMoney(@Param("earnedMoney") BigDecimal earnedMoney,@Param("id") Long id);*/
}
