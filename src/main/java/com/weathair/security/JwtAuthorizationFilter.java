package com.weathair.security;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.Properties;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JwtAuthorizationFilter extends UsernamePasswordAuthenticationFilter {

	// The time in milli-second before your session expire, here 3600 Seconds, so 1 hour
	//private final static long EXPIRATION_TIME = 3600000L;
	
	// a secret Key to avoid password hack by dictionary for exemple
//	private final static String SECRET_KEY = "869EOKLMPHI32389JDC23E389CDKJ32443";
	
	private final AuthenticationManager authenticationManager;
	
	public JwtAuthorizationFilter (AuthenticationManager authenticationManager) {

		this.authenticationManager = authenticationManager;
		// Show the root for login, we didn't get control for authentication so we make root here.
		setFilterProcessesUrl("/login");
	}
	

	/**
	 * We try to authenticate here,
	 * We read our informations in our pojo class and then we map it with RequestBody
	 * We return autehntication Manager, where we got a new object with an user name, a password
	 *  and an arraylist empty where you will found the roles
	 * 
	 */
	@Override
	public Authentication attemptAuthentication (HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {
		
		try {
			InputStream requestBody = request.getInputStream();
			AuthenticationPojo authenticationInfo = new ObjectMapper().readValue(requestBody, AuthenticationPojo.class);
			

			return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationInfo.getUsername(),
					authenticationInfo.getPassword(), new ArrayList<>()));
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * If your authentication is successfull so this function will put on , then
	 * we create a JWT Token, we put him expiration date and we sign it with 
	 * HMAC512 method,
	 * Every token must began with "beared ", that's why we writed it on the response
	 */
	@Override
	protected void successfulAuthentication (HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authenticationResult) throws IOException, ServletException {
	
		Properties properties = new Properties();
		try {
			properties.load(new FileInputStream("src/main/resources/application.properties"));
		} catch (IOException e) {
			System.err.println(e.getMessage());
		}

		String SECRET_KEY = properties.getProperty("jwt.secret_key");
		String TOKEN_COOKIE = properties.getProperty("jwt.auth_name");
		Long EXPIRATION_TIME = Long.parseLong(properties.getProperty("jwt.expiration_time"));
		Boolean COOKIE_SECURE = Boolean.parseBoolean(properties.getProperty("jwt.cookie_secure"));
		
		String token = JWT.create().withSubject(authenticationResult.getName())
				.withExpiresAt(new Date(System.currentTimeMillis() + EXPIRATION_TIME*1000))
				.sign(Algorithm.HMAC512(SECRET_KEY));
		response.addHeader("Authorization", "Bearer " + token);
		
		ResponseCookie responseCookie = ResponseCookie.from(TOKEN_COOKIE, token).httpOnly(true).maxAge(EXPIRATION_TIME*1000)
				.path("/").sameSite("lax").secure(COOKIE_SECURE).build();
		response.setHeader(HttpHeaders.SET_COOKIE, responseCookie.toString());
	}
	
	
}
