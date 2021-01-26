package com.weathair.security;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Date;
import java.util.Properties;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.filter.OncePerRequestFilter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

public class JwtAuthenticationFilter extends OncePerRequestFilter {

	// private final static String SECRET_KEY = "869EOKLMPHI32389JDC23E389CDKJ32443";

	private final UserDetailsService userDetailsService;

	public JwtAuthenticationFilter(UserDetailsService userDetailsService) {
		this.userDetailsService = userDetailsService;
	}

	/**
	 * With substring 7 & trim we delete "Bearer ", in order to just keep our token,
	 * Then we take all the caracters in the token with .getSubject()
	 * And we make a new UserDetail who can load by him UserName.
	 */
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

//		String header = request.getHeader("Authorization");
//		// String cookie =  request.getCookies("Authorization");
//		if (header != null && header.startsWith("Bearer ")) {
//			String token = header.substring(7).trim();
//
//			DecodedJWT decodedJWT = JWT.require(Algorithm.HMAC512(SECRET_KEY)).build().verify(token);
//			String username = decodedJWT.getSubject();
//
//			UserDetails user = userDetailsService.loadUserByUsername(username);
//			Authentication authentication = new UsernamePasswordAuthenticationToken(username, null,
//					user.getAuthorities());
//			SecurityContextHolder.getContext().setAuthentication(authentication);
//		}

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

		// ------------------------

		if (request.getCookies() != null) {
			for (Cookie cookie : request.getCookies()) {
				if (cookie.getName().equals(TOKEN_COOKIE)) {
					String token = cookie.getValue();
					DecodedJWT decodedJWT = JWT.require(Algorithm.HMAC512(SECRET_KEY)).build().verify(token);
					String username = decodedJWT.getSubject();
					UserDetails user = userDetailsService.loadUserByUsername(username);
					Authentication authentication = new UsernamePasswordAuthenticationToken(username, null,
							user.getAuthorities());
					SecurityContextHolder.getContext().setAuthentication(authentication);
					// create the new token
					String newToken = JWT.create().withSubject(username)
							.withExpiresAt(new Date(System.currentTimeMillis() + EXPIRATION_TIME*1000))
							.sign(Algorithm.HMAC512(SECRET_KEY));
					ResponseCookie responseCookie = ResponseCookie.from(TOKEN_COOKIE, newToken).httpOnly(true)
							.maxAge(EXPIRATION_TIME).path("/").sameSite("lax").secure(COOKIE_SECURE).build();
					response.setHeader(HttpHeaders.SET_COOKIE, responseCookie.toString());
				}
			}
		}
		
		
		filterChain.doFilter(request, response);

	}

}
