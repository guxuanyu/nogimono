package com.ganger.repository;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.ganger.entity.User;

public interface UserRepository extends JpaRepository<User, Integer>{
	/**
	 * 根据id查找用户
	 * @param id
	 * @return
	 */
	Optional<User> findById(Integer id);

	/**
	 * 根据电话号码查找用户
	 * @param phone
	 * @return
	 */
	User findByPhone(String phone);

	/**
	 * 根据邮箱查找用户
	 * @param email
	 * @return
	 */
	User findByEmail(String email);

	/**
	 * 根据昵称查找用户
	 * @param nickname
	 * @return
	 */
	User findByNickname(String nickname);
}
