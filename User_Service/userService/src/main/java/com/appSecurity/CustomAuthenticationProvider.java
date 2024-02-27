package com.appSecurity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;


@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

	@Autowired CustomUserDetailService customUserDetailService;
	
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		// TODO Auto-generated method stub
		String username=authentication.getName();
		String password=authentication.getCredentials().toString();
		System.out.println(username+"::::"+password);
		UserDetails userDetails=customUserDetailService.loadUserByUsername(username);
		System.out.println("data base user name--:"+userDetails.getUsername()+":::"+userDetails.getPassword());
		if(userDetails != null && userDetails.getPassword().equals(password)) {
			return new UsernamePasswordAuthenticationToken(userDetails, password, userDetails.getAuthorities());
		}else{
			return new UsernamePasswordAuthenticationToken(username,password);
		}
		/*else{
			return new UsernamePasswordAuthenticationToken()
		}*/
	}

	@Override
	public boolean supports(Class<?> authentication) {
		// TODO Auto-generated method stub
		System.out.println("support method of customauthentication provider");
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}
}
