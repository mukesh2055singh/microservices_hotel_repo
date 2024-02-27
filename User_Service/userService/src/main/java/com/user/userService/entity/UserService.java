package com.user.userService.entity;

import java.util.List;

public interface UserService {
	public UserBean saveUser(UserBean userBean);
	public List<UserBean> getAllUser();
	UserBean getUser(Integer id);
}
