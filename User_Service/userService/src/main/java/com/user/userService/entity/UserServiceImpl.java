package com.user.userService.entity;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery.FetchableFluentQuery;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


@Service
public class UserServiceImpl implements UserService {

	@Autowired private UserRepository userRepository;
	//@Autowired RestTemplate restTemplate;
	@Override
	public UserBean saveUser(UserBean userBean) {
		// TODO Auto-generated method stub
		return userRepository.save(userBean);
	}

	@Override
	public List<UserBean> getAllUser() {
		// TODO Auto-generated method stub
		return userRepository.findAll();
	}

	@Override
	public UserBean getUser(Integer id) {
		// TODO Auto-generated method stub
		UserBean userBean=new UserBean();
		try {
			userBean=userRepository.findByUserId(id);
			//ArrayList<Ratings> listRatingBean=restTemplate.getForObject("http://localhost:8087/ratings/user/"+userBean.getUserId(),ArrayList.class);
			//userBean.setRatings(listRatingBean);
			System.out.println(userBean.getUsername());
		}catch(Exception e) {
			e.printStackTrace();
			throw new UserNotFoundException("usernot found on this id--:"+id);
		}
		return userBean;
	}
}
