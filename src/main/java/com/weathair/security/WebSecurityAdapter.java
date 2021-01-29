package com.weathair.security;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityAdapter extends WebSecurityConfigurerAdapter {

	private UserDetailsService userDetailsService;
	private PasswordEncoder passwordEncoder;

	public WebSecurityAdapter(UserDetailsService userDetailsService, PasswordEncoder passwordEncoder) {
		this.userDetailsService = userDetailsService;
		this.passwordEncoder = passwordEncoder;
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
	}
	
	
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		Properties properties = new Properties();
		try {
			properties.load(new FileInputStream("src/main/resources/application.properties"));
		} catch (IOException e) {
			System.err.println(e.getMessage());
		}
		String TOKEN_COOKIE = properties.getProperty("jwt.auth_name");
		
		http.authorizeRequests()
			.antMatchers(HttpMethod.POST, "/login").permitAll()  
			.antMatchers(HttpMethod.GET, "/townships/**").permitAll()
			.antMatchers(HttpMethod.GET, "/meteoindicators/**").permitAll()
			.antMatchers(HttpMethod.GET, "/gpscoordinates/**").permitAll()
			.antMatchers(HttpMethod.GET, "/airindicators/**").permitAll()
			.antMatchers(HttpMethod.GET, "/utils/**").permitAll()
			.antMatchers(HttpMethod.GET, "/forum/topics/**").permitAll()
			.antMatchers(HttpMethod.GET, "/forum/posts/**").permitAll()
			.antMatchers("/users/*").permitAll()
			.antMatchers("/**")
			.authenticated()
			.and()
			.sessionManagement()
			.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
			.and()
			.exceptionHandling()
			.authenticationEntryPoint(
				(request, response, authException) -> response.setStatus(HttpServletResponse.SC_FORBIDDEN))
			
			.and()
			
			.httpBasic()
			
			.and()
			.csrf()
			.disable()
			.cors()
			.and()
			.addFilter(new JwtAuthorizationFilter(authenticationManager()))
			.addFilterBefore(new JwtAuthenticationFilter(userDetailsService), BasicAuthenticationFilter.class)
			.logout().logoutUrl("/logout")
			.logoutSuccessHandler((req, resp, auth) -> {
				resp.setStatus(HttpServletResponse.SC_OK);
			})
			.deleteCookies(TOKEN_COOKIE);
		
			http.headers().frameOptions().sameOrigin();
	

			
	}
	
	

}
