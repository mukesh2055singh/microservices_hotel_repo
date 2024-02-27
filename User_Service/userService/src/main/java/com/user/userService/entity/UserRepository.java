package com.user.userService.entity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserBean, Integer>{
	public UserBean findByUserId(Integer id);
	public UserBean findByUsername(String username);
}
