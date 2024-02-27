package com.appSecurity;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;
import java.util.function.Function;

import javax.crypto.spec.SecretKeySpec;
import javax.servlet.http.HttpServletRequest;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtTokenProviderImpl implements JwtTokenProvider{
	
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
	
	private final Key secretKey = Keys.secretKeyFor(SignatureAlgorithm.HS256);
    //@Autowired private Key key;
	
    @Override
	public String createToken(Authentication authentication) {
    	CustomUserDetails userDetails=(CustomUserDetails)authentication.getPrincipal();
		System.out.println("createtoken method called--:"+userDetails.getUsername());
		// TODO Auto-generated method stub
		System.out.println("key--:"+getSigningKey());
		return Jwts.builder()
				.setSubject(userDetails.getUsername())
		        .setIssuedAt(new Date())
		        .setExpiration(new Date(System.currentTimeMillis()+50000000))
		        .signWith(SignatureAlgorithm.HS256,getSigningKey())
		        .compact();
	}

	@Override
	public Authentication getAuthentication(String token) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean validateToken(HttpServletRequest request,String token,UserDetails userDetails) {
		// TODO Auto-generated method stub
		boolean status=false;
		final Date expiration = getExpirationDateFromToken(token);
		final String username = getUsernameFromToken(token);
		System.out.println(expiration);
		if(username.equals(userDetails.getUsername()) && expiration.after(new Date())){
			status=true;
		}
		System.out.println("status --:"+status);
		return status;
	}

	@Override
	public String getUsernameFromToken(String token) {
		// TODO Auto-generated method stub
		String userName= getClaimFromToken(token).getSubject();
		System.out.println("userName--:"+userName);
		return userName;
	}

	@Override
	public Date getExpirationDateFromToken(String token) {
		// TODO Auto-generated method stub
		Date expiryDate= getClaimFromToken(token).getExpiration();
		return expiryDate;
	}
	public Claims getClaimFromToken(String token) {
        final Claims claims = Jwts.parser().setSigningKey(getSigningKey()).parseClaimsJws(token).getBody();
        return claims;
    }
	

}
