package com.appSecurity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.user.userService.entity.UserBean;
import com.user.userService.entity.UserRepository;

@Service
public class CustomUserDetailService implements UserDetailsService {
	@Autowired UserRepository userRepo;
	
	public CustomUserDetailService() {
		System.out.println("CustomUserDetailService constructor called");
	}
	
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		System.out.println("loadUserByUsername method called");
		// TODO Auto-generated method stub
		CustomUserDetails userDetails=null;
		try {
			UserBean userBean=userRepo.findByUsername(username);
			System.out.println(userBean.getUsername()+"::"+userBean.getPassword());
			if(userBean != null) {
				userDetails=new CustomUserDetails(userBean.getUsername(),userBean.getPassword(),null);
				System.out.println(userDetails);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return userDetails;
	}

}
