package com.appSecurity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
	
	@Autowired
	CustomAuthenticationProvider customAuthenticationProvider;
	@Autowired CustomUserDetailService customUserDetailService;
	@Autowired
	private JwtTokenProvider jwtTokenProvider;
	
	@Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		System.out.println("configurer authenticationbuilder method");
        auth.authenticationProvider(customAuthenticationProvider);
        // Alternatively, you can use userDetailsService
        // auth.userDetailsService(customUserDetailsService).passwordEncoder(passwordEncoder());
    }
	
	@Override
    protected void configure(HttpSecurity http) throws Exception {
		System.out.println("security configuration method called---:"+jwtTokenProvider+":::"+customUserDetailService);
		http.cors().and().csrf().disable()
        .authorizeRequests()
        .antMatchers("/user/doLogin").permitAll()
        .anyRequest().authenticated()
        //.and()
        //.formLogin().loginPage("/user/showLogin")
        //.loginProcessingUrl("/user/doLogin")
        //.permitAll()
        .and()
        //.addFilterBefore(new JwtTokenFilter(jwtTokenProvider) , UsernamePasswordAuthenticationFilter.class)
        .apply(new JwtConfigurer(jwtTokenProvider,customUserDetailService))
        .and()
        .logout()
        .permitAll();
    }
}
