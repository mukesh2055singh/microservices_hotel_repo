package com.user.userService.entity;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/users")
public class UserController {
	@Autowired UserServiceImpl userServiceImpl;
	
	@PostMapping
	public ResponseEntity<UserBean> createUser(@RequestBody UserBean userBean){
		userBean=userServiceImpl.saveUser(userBean);
		return ResponseEntity.status(HttpStatus.CREATED).body(userBean);
	}
	@GetMapping("/{userId}")
	//@CircuitBreaker(name="ratingBreaker", fallbackMethod="ratingFallBackMethod")
	public ResponseEntity<UserBean> getUser(@PathVariable Integer userId){
		UserBean userBean=userServiceImpl.getUser(userId);
			return ResponseEntity.ok(userBean);
	}
	@GetMapping
	public ResponseEntity<List<UserBean>> getAllUser(){
		List<UserBean> allUser=userServiceImpl.getAllUser();
		 return ResponseEntity.ok(allUser);
	}
	
	public ResponseEntity<UserBean> ratingFallBackMethod(Integer userId,Exception ex){
		System.out.println(ex.getMessage());
		UserBean user=new UserBean();
		user.setUsername("dumm@gmai");
		return new ResponseEntity<>(user,HttpStatus.OK);
	}
}
