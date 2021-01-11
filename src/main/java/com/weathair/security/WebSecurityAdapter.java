package com.weathair.security;

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
			
			.antMatchers(HttpMethod.GET, "/townships")
			.permitAll()
			.antMatchers(HttpMethod.GET, "/meteoindicators")
			.permitAll()
			.antMatchers(HttpMethod.GET, "/airindicators")
			.permitAll()
			.antMatchers(HttpMethod.GET, "/posts")
			.permitAll()
			.antMatchers(HttpMethod.GET, "/topics")
			.permitAll()
			.antMatchers(HttpMethod.GET, "/messages")
			.permitAll()

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
			.disable();
	}
}
