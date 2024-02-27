package com.appSecurity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

@Component
public class JwtConfigurer extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {

    private JwtTokenProvider jwtTokenProvider;
    private CustomUserDetailService customUserDetailService;
    
    public JwtConfigurer(JwtTokenProvider jwtTokenProvider,CustomUserDetailService customUserDetailService) {
    	System.out.println("jwt configure constructore called");
        this.jwtTokenProvider = jwtTokenProvider;
        this.customUserDetailService=customUserDetailService;
    }

    @Override
    public void configure(HttpSecurity http) {
    	System.out.println("jwt congurer method called--:"+customUserDetailService);
        JwtTokenFilter customFilter = new JwtTokenFilter(jwtTokenProvider,customUserDetailService);
        try {
			http.addFilterBefore(customFilter, UsernamePasswordAuthenticationFilter.class);
			//csrf().disable();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}


