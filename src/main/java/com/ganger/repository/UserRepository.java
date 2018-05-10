package com.ganger.repository;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.ganger.entity.User;

public interface UserRepository extends JpaRepository<User, Integer>{
	Optional<User> findById(Integer id);
	User findByPhone(String phone);
	User findByEmail(String email);
	User findByNickname(String nickname);
}
