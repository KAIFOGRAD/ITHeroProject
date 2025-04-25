package com.ithero.geomap.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ithero.geomap.Entity.User.User;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    /**
     * Находит всех пользователей с заданным именем
     * 
     * @param login имя пользователя
     * @param email почта
     */
    List<User> findByLoginOrEmail(String login, String email);

    List<User> findByName(String name);

    List<User> findByLogin(String login);

    boolean existsByLogin(String login);

}
