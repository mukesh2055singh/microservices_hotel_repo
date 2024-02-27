package com.user.userService.entity;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.appSecurity.CustomAuthenticationProvider;
import com.appSecurity.CustomUserDetails;
import com.appSecurity.JwtTokenProvider;

@RestController
@RequestMapping("/user")
public class LoginController {
	@Autowired CustomAuthenticationProvider customAuthenticationProvider;
	@Autowired private JwtTokenProvider jwtTokenProvider;
	@PostMapping("/doLogin")
	public ResponseEntity<LoginBeanResponse> authenticateUserLogin(@RequestBody LoginBean loginBean,HttpServletRequest request,HttpServletResponse response) {
		System.out.println("aunticate user login mapping called");
		Authentication auth=new UsernamePasswordAuthenticationToken(loginBean.getUsername(),loginBean.getPassword());
		Authentication authentication=customAuthenticationProvider.authenticate(auth);
		System.out.println("authentication---:"+authentication);
		LoginBeanResponse loginBeanResponse=new LoginBeanResponse();
		if(authentication != null && authentication.isAuthenticated()) {
			System.out.println("user authenticated");
			String jwtToken=jwtTokenProvider.createToken(authentication);
			System.out.println("createdToken--:"+jwtToken);
			/*ResponseCookie cookie = ResponseCookie.from("createdToken", createdToken)
		            .maxAge(!(createdToken.equals("")) ? 60*60*24 : 0)
		            .httpOnly(true)
		            .domain("localhost")
		            .sameSite("None")
		            .secure(false)
		            .path("/")
		            .build();
			response.setHeader("Authorization", "Bearer " +createdToken);
			//response.addCookie(cookie);
			response.setHeader("Set-Cookie", cookie.toString());
			response.setHeader("Authorization", "Bearer " +createdToken);
	        Cookie cookie = new Cookie("jwtToken",createdToken);
	        cookie.setPath("/");
	        cookie.setHttpOnly(false);
	        cookie.setMaxAge(60*60*60*24);
	        cookie.setDomain("localhost");
	        cookie.setSecure(false);
	        response.addCookie(cookie);*/
			loginBeanResponse.setJwtToken(jwtToken);
			loginBeanResponse.setUsername(((CustomUserDetails)authentication.getPrincipal()).getUsername());

			//return ResponseEntity.ok(loginBeanResponse);
		}else {
			System.out.println("user not authenticated");
			loginBeanResponse.setJwtToken("");
			loginBeanResponse.setUsername("Invalid User");
			//return ResponseEntity.ok("Invalid Users");
		}
		return ResponseEntity.ok(loginBeanResponse);
	}
	@GetMapping("/apiGatewayAuthentication")
	public ResponseEntity<String> protectedEndpoint(@CookieValue(name = "jwtToken", required = false) String jwtToken) {
        System.out.println("testingData --:");
		if (jwtToken != null) {
        	System.out.println("accessToken--:"+jwtToken);
            return ResponseEntity.ok("Access granted");
        } else {
        	System.out.println("accessToken--:"+jwtToken);
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized");
        }
    }
	@GetMapping("/doLogin11")
	public String protectedEndpointestt() {
        System.out.println("testingData --:");
        return "1";
		
    }
}
