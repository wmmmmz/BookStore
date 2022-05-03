package com.wmz.bookstore.repository;

import com.wmz.bookstore.pojo.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {

    User save(User user);

    User findUserByUsername(String username);

    User findUserByUsernameAndPassword(String username, String password);
}
