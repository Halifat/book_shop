package com.vladkazakov.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vladkazakov.entities.User;

@Repository("userRepository")
public interface UserRepository extends JpaRepository<User, Integer> {
	User findByLoginAndPassword(String login, String password);

	User findByLogin(String login);

}
