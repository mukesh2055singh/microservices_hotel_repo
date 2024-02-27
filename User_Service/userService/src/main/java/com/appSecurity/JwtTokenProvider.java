package com.appSecurity;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;

public interface JwtTokenProvider {
	public String createToken(Authentication authentication);

    public Authentication getAuthentication(String token);

    public boolean validateToken(HttpServletRequest request,String token,UserDetails userDetails);
    public String getUsernameFromToken(String token);
    public Date getExpirationDateFromToken(String token);
}
