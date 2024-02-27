package com.appSecurity;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

import javax.crypto.spec.SecretKeySpec;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;


@Component
public class JwtTokenFilter extends OncePerRequestFilter {
	
	//@Autowired
	private CustomUserDetailService customUserDetailService;
	private JwtTokenProvider jwtTokenProvider;
	
	private static final String SECRET_KEY = "mukeshsingh"; // Change this to a strong, secure secret key
	private String getSigningKey() {
		byte[] keyBytes = SECRET_KEY.getBytes(StandardCharsets.UTF_8);
		return bytesToHex(new SecretKeySpec(keyBytes, "HmacSHA256").getEncoded());
	}
	
	private static String bytesToHex(byte[] bytes) {
        StringBuilder result = new StringBuilder();
        for (byte b : bytes) {
            result.append(String.format("%05x", b));
        }
        return result.toString();
    }
    
    public JwtTokenFilter(JwtTokenProvider jwtTokenProvider,CustomUserDetailService customUserDetailService) {
    	System.out.println("JwtTokenFilter constructor 11111");
        this.jwtTokenProvider = jwtTokenProvider;
        this.customUserDetailService=customUserDetailService;
    }
   
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
		Cookie[] cookies = request.getCookies();
        logger.info("Successfully authenticated user  " +request.getRequestURI()+":---customUserDetailService--:"+request.getRequestURL());
        String cookieValue="";
        if (cookies != null) {
            for (Cookie cookie : cookies) {
            	System.out.println("cookie --:"+cookie.getName()+":--cookie value--:"+cookie.getValue());
                if ("jwtToken".equals(cookie.getName())) {
                    cookieValue = cookie.getValue();
                    System.out.println("Cookie Value: " + cookieValue);
                    break; // Break out of the loop since you found the desired cookie
                }
            }
        }
        if(!cookieValue.equals("")) { 
        	System.out.println("key--:"+getSigningKey()+":---customUserDetailService--:"+customUserDetailService);
        	String username=jwtTokenProvider.getUsernameFromToken(cookieValue);
        	if(!(username.equals("")) && SecurityContextHolder.getContext().getAuthentication() == null) {
        		UserDetails userDetails=customUserDetailService.loadUserByUsername(username);
        		if(jwtTokenProvider.validateToken(request,cookieValue,userDetails)) {
        			System.out.println("user is authenticated");
        			UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                    authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authentication);
        		}
        	}        	//SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        
        filterChain.doFilter(request, response);
		}catch(Exception e) {
			e.printStackTrace();
		}
    }

    @Override
    protected boolean shouldNotFilterAsyncDispatch() {
        return false;
    }

    @Override
    protected boolean shouldNotFilterErrorDispatch() {
        return false;
    }

}
