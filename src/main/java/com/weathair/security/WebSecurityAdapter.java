package com.weathair.security;

import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Bean;
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
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

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
		http.authorizeRequests()
			.antMatchers(HttpMethod.POST, "/login").permitAll()  
			.antMatchers(HttpMethod.GET, "/login").permitAll() 
			.antMatchers(HttpMethod.GET, "/townships").permitAll()
			.antMatchers(HttpMethod.GET, "/meteoindicators").permitAll()
			.antMatchers(HttpMethod.GET, "/airindicators").permitAll()
			.antMatchers(HttpMethod.GET, "/utils").permitAll()
			.antMatchers(HttpMethod.GET, "/posts").permitAll()
			.antMatchers("/topics").permitAll()
			.antMatchers("/messages").permitAll()
			.antMatchers("/users").permitAll()
			
//			.antMatchers(HttpMethod.POST, "/topics").hasRole("ADMINISTRATOR")
//			.antMatchers(HttpMethod.PUT, "/topics").hasRole("ADMINISTRATOR")
//			.antMatchers(HttpMethod.DELETE, "/topics").hasRole("ADMINISTRATOR")
			
//			.antMatchers(HttpMethod.POST, "/posts").hasRole("USER")
//			.antMatchers(HttpMethod.POST, "/posts").hasRole("ADMINISTRATOR")
//			.antMatchers(HttpMethod.PUT, "/posts").hasRole("ADMINISTRATOR")
//			.antMatchers(HttpMethod.DELETE, "/posts").hasRole("ADMINISTRATOR")
			
//			.antMatchers(HttpMethod.POST, "/messages").hasRole("USER")
//			.antMatchers(HttpMethod.POST, "/messages").hasRole("ADMINISTRATOR")
//			.antMatchers(HttpMethod.PUT, "/messages").hasRole("ADMINISTRATOR")
//			.antMatchers(HttpMethod.DELETE, "/messages").hasRole("ADMINISTRATOR")
			
//			.antMatchers(HttpMethod.GET, "/notifications").hasRole("USER")
//			.antMatchers("/notifications").hasRole("ADMINISTRATOR")
			
//			.antMatchers("/favorites").hasRole("USER")
//			.antMatchers("/favorites").hasRole("ADMINISTRATOR")
			

			
			.antMatchers("/**")
			.authenticated()
			
			.and()
			.addFilter(new JwtAuthorizationFilter(authenticationManager()))
			.addFilterAfter(new JwtAuthenticationFilter(userDetailsService), BasicAuthenticationFilter.class)
			
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
			.cors();
	}
}
